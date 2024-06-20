package dev.zieger.bitcointracker.app.di

import dev.zieger.bitcointracker.data.bybit.repository.BitcoinTrackerRepositoryImpl
import dev.zieger.bitcointracker.data.bybit.repository.ByBitRepository
import dev.zieger.bitcointracker.data.bybit.repository.RoomRepository
import dev.zieger.bitcointracker.domain.repositories.BitcoinTrackerRepository
import org.koin.dsl.module

val dataModule = module {

    single { ByBitRepository() }
    single { RoomRepository(get()) }
    single<BitcoinTrackerRepository> { BitcoinTrackerRepositoryImpl(get(), get()) }
}