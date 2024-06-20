package dev.zieger.bitcointracker.data.bybit.dto

import dev.zieger.bitcointracker.domain.entities.Candle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WsKline(
    @SerialName("start")
    override val openTime: Long,
    @SerialName("end")
    val closeTime: Long,
    val interval: String,
    override val open: Double,
    override val close: Double,
    override val high: Double,
    override val low: Double,
    override val volume: Double,
    val turnover: Double,
    val confirm: Boolean,
    val timestamp: Long
): Candle