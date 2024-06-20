package dev.zieger.bitcointracker.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import org.koin.android.ext.android.inject

class BitcoinTrackerFragment : Fragment() {

    private val bitcoinTrackerViewModel: BitcoinTrackerViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        )
        setContent {
            BitcoinTrackerScreen(bitcoinTrackerViewModel)
        }
    }
}