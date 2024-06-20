package dev.zieger.bitcointracker.domain.entities

interface Candle {
    val openTime: Long
    val open: Double
    val high: Double
    val low: Double
    val close: Double
    val volume: Double
}