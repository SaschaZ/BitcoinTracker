package dev.zieger.bitcointracker.app.di

import dev.zieger.bitcointracker.app.ui.BitcoinTrackerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { BitcoinTrackerViewModel(get()) }
}