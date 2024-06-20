package dev.zieger.bitcointracker.data.bybit

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ClientProvider {

    val newClient: HttpClient get() = HttpClient(OkHttp) {
        engine {
            config {
                followSslRedirects(true)
                followRedirects(true)
                retryOnConnectionFailure(true)
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(WebSockets)
    }
}