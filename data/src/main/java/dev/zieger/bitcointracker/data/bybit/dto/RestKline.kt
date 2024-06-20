package dev.zieger.bitcointracker.data.bybit.dto

import dev.zieger.bitcointracker.domain.entities.Candle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = KlineListSerializer::class)
data class RestKline(
    override val openTime: Long,
    override val open: Double,
    override val high: Double,
    override val low: Double,
    override val close: Double,
    override val volume: Double
) : Candle {
    constructor(list: List<Double>) : this(
                list[0].toLong(), list[1], list[3], list[2], list[4], list[5]
    )
}

object KlineListSerializer : KSerializer<RestKline> {

    private val doubleSerializer = ListSerializer(Double.serializer())
    override val descriptor: SerialDescriptor
        get() = doubleSerializer.descriptor

    private val RestKline.toList: List<Double>
                get() = listOf(openTime.toDouble(), open, high, low, close, volume)

    override fun serialize(encoder: Encoder, value: RestKline) =
        encoder.encodeSerializableValue(doubleSerializer, value.toList)

    override fun deserialize(decoder: Decoder): RestKline =
        RestKline(decoder.decodeSerializableValue(doubleSerializer))
}

@Serializable
data class KlineListContainer(
    val category: String? = null,
    val symbol: String? = null,
    val list: List<RestKline>? = null
)