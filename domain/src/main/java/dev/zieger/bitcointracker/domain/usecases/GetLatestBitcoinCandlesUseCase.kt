package dev.zieger.bitcointracker.domain.usecases

import dev.zieger.bitcointracker.domain.entities.Candle
import dev.zieger.bitcointracker.domain.repositories.BitcoinTrackerRepository
import kotlinx.coroutines.flow.Flow

class GetLatestBitcoinCandlesUseCase(
    private val repository: BitcoinTrackerRepository
) {
    fun execute(): Flow<List<Candle>> = repository.getCandles()
}