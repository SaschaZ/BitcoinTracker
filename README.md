# Bitcoin Tracker

This project demonstrates the use of Clean Architecture in an Android app using a simple Bitcoin tracker, which shows the latest Bitcoin prices. It shows the close prices of the daily candles from the last two weeks. Additionally the app subscribes to a web socket and updates the UI with the latest price.


## Tech Stack

* [Kotlin](https://kotlinlang.org/)
* [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
* [Jetpack Compose](https://developer.android.com/develop/ui/compose) - UI creation
* [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - parsing JSON
* [Ktor](https://ktor.io/) - client for REST and WebSocket backends
* [Room](https://developer.android.com/training/data-storage/room) - storing downloaded data
* [Koin](https://insert-koin.io/) - dependency injection


## Architecture

The code in this project is divided into three modules:

**data**

* Contains the repositories that communicate with the components outside of the app: ByBit exchange and persistent data storage.
* This modile can only access code of the domain module.
* Code in this module should not be used from the UI code directly. Only the usecases in the domain module are allowed to use the repositories of the data module.
* To make the repositories inside this module available in the domain module, the domain module contains interfaces of the required repositories. These interfaces need to be implemented by the repositories inside this module.

**domain**

* Contains the entities, repository definitions and usercases of the app.
* This modile can only access code of the domain module.
* The usecase will be the only connection from the ViewModels of the app module to the repositories of the data module. A typical usecase will look like this:

    ```kotlin
    class GetLatestBitcoinCandlesUseCase(
        private val repository: BitcoinTrackerRepository
    ) {
        fun execute(): Flow<List<Candle>> = repository.getCandles()
    }
    ````
    Most likely the only call a repository and return the result.

**app**

* Contains everything UI related like Activites, Fragments, ViewModels and Composables.
* In this module only the usecases of the domain model are allowed to use by the ViewModels.
* The ViewModels provide properties of the type `Flow` for the Composables. The Composables read these `Flow`s with the `collectAsState` method to get the data from the ViewModels.
* A Comoosable itself will never call a usecase directly. Each interaction to the ViewModel needs to be done with a seperate method inside the specific ViewModel. The ViewModel will than call the required usecase if required and change all `Flow` properties accordingly.


## What's next?

* implement selection for coin and interval
* use a candle stick chart instead of a table for the prices
* make this app running on iOS using kotlin multiplatform