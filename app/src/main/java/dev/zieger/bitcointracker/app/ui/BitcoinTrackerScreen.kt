package dev.zieger.bitcointracker.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat

@Composable
fun BitcoinTrackerScreen(
    viewModel: BitcoinTrackerViewModel
) {
    val prices = viewModel.bitcoinPrices.collectAsState(emptyList()).value

    Row(
        Modifier.padding(8.dp)
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(0.5f)
        ) {
            val formatter = SimpleDateFormat.getDateInstance()
            items(prices) {candle ->
                Text("${formatter.format(candle.openTime)}: ${candle.close}$")
            }
        }

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("latest price:")
            Text("${prices.firstOrNull()?.close}$")
        }
    }
}