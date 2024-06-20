package dev.zieger.bitcointracker.domain.repositories

import dev.zieger.bitcointracker.domain.entities.Candle
import kotlinx.coroutines.flow.Flow

interface BitcoinTrackerRepository {

    fun getCandles(): Flow<List<Candle>>
}