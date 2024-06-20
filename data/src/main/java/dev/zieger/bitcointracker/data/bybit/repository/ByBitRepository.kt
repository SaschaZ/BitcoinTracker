package dev.zieger.bitcointracker.data.bybit.repository

import dev.zieger.bitcointracker.data.bybit.ClientProvider
import dev.zieger.bitcointracker.data.bybit.dto.KlineListContainer
import dev.zieger.bitcointracker.data.bybit.dto.RestResponse
import dev.zieger.bitcointracker.data.bybit.dto.WsKline
import dev.zieger.bitcointracker.domain.entities.Candle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.get
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class ByBitRepository {

    companion object {

        private const val REST_BASE = "https://api-testnet.bybit.com"
        private const val WS_BASE = "wss://stream.bybit.com/v5/public/spot"
    }

    private val client: HttpClient = ClientProvider.newClient

    suspend fun restBitcoinCandles(): List<Candle>? =
        client
            .get("$REST_BASE/v5/market/kline?category=spot&symbol=BTCUSDT&interval=D")
            .body<RestResponse<KlineListContainer>>()
            .result
            ?.list
            ?.take(14) // only return the last 14 days

    fun wsBitcoinCandles(): Flow<Candle> = flow {
        client.webSocket(WS_BASE) {
            val subscribeMessage = buildSubscribeMessage("kline.D.BTCUSDT")
            send(Frame.Text(subscribeMessage))

            for (frame in incoming) {
                frame as? Frame.Text ?: continue

                val receivedText = frame.readText()
                if ("\"success\":true" in receivedText) continue

                emit(receivedText.fromJson())
            }
        }
    }

    private fun buildSubscribeMessage(
        topic: String
    ): String = """
            {
                "op": "subscribe",
                "args": ["$topic"]
            }
        """.trimIndent()

    private fun String.fromJson(): WsKline =
        Json.decodeFromString<SnapshotFrame<WsKline>>(this).data.first()

    @Serializable
    private data class SnapshotFrame<out T>(
        val type: String,
        val topic: String,
        val data: List<T>,
        val ts: Long
    )
}
