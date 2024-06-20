package dev.zieger.bitcointracker.app.di

import dev.zieger.bitcointracker.domain.usecases.GetLatestBitcoinCandlesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetLatestBitcoinCandlesUseCase(get()) }
}