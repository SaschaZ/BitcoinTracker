package dev.zieger.bitcointracker.data.bybit.dto

import kotlinx.serialization.Serializable

@Serializable
data class  RestResponse<out T>(
    val retCode: Int = -1,
    val retMsg: String? = null,
    val result: T? = null,
    val time: Long? = null
)