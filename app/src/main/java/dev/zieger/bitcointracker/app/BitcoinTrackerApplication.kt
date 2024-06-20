package dev.zieger.bitcointracker.app

import android.app.Application
import dev.zieger.bitcointracker.app.di.appModule
import dev.zieger.bitcointracker.app.di.dataModule
import dev.zieger.bitcointracker.app.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BitcoinTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BitcoinTrackerApplication)
            modules(
                appModule,
                domainModule,
                dataModule
            )
        }
    }
}