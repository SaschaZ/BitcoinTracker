package dev.zieger.bitcointracker.data.bybit.repository

import dev.zieger.bitcointracker.domain.entities.Candle
import dev.zieger.bitcointracker.domain.repositories.BitcoinTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BitcoinTrackerRepositoryImpl(
    private val byBitRepository: ByBitRepository,
    private val roomRepository: RoomRepository
) : BitcoinTrackerRepository {

    override fun getCandles(): Flow<List<Candle>> = flow {
        emit(roomRepository.getCandles().sortedByDescending { it.openTime })

        byBitRepository.restBitcoinCandles()?.let { rest ->
            roomRepository.deleteCandles()
            roomRepository.insertCandles(rest)
            emit(rest.sortedByDescending { it.openTime })
        }

        byBitRepository.wsBitcoinCandles().collect { candle ->
            val stored = roomRepository.getCandles()
            val latest = (listOf(candle) + stored.filterNot { it.openTime == candle.openTime })

            roomRepository.deleteCandles()
            roomRepository.insertCandles(latest)
            emit(latest.sortedByDescending { it.openTime })
        }
    }
}