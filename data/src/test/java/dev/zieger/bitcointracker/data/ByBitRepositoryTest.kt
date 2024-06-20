package dev.zieger.bitcointracker.data

import dev.zieger.bitcointracker.data.bybit.repository.ByBitRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ByBitRepositoryTest {

    @Test
    fun restTest(): Unit = runBlocking {
        assert(ByBitRepository().restBitcoinCandles()?.isNotEmpty() == true)
    }

    @Test
    fun webSocketTest(): Unit = runBlocking {
        ByBitRepository().wsBitcoinCandles().collect { candle ->
            println("ws candle: $candle")
        }
    }
}