package dev.zieger.bitcointracker.app.ui

import androidx.lifecycle.ViewModel
import dev.zieger.bitcointracker.domain.usecases.GetLatestBitcoinCandlesUseCase

class BitcoinTrackerViewModel(
    getLatestBitcoinCandlesUseCase: GetLatestBitcoinCandlesUseCase
) : ViewModel() {

    val bitcoinPrices = getLatestBitcoinCandlesUseCase.execute()
}