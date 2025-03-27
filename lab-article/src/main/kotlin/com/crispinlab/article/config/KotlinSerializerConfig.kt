package com.crispinlab.article.config

import java.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

@Configuration
class KotlinSerializerConfig {
    @Bean
    fun instantSerializer() =
        object : KSerializer<Instant> {
            override val descriptor: SerialDescriptor =
                PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

            override fun serialize(
                encoder: Encoder,
                value: Instant
            ) {
                encoder.encodeString(value.toString())
            }

            override fun deserialize(decoder: Decoder): Instant =
                Instant.parse(decoder.decodeString())
        }

    @Bean
    fun serializersModule(instantSerializer: KSerializer<Instant>): SerializersModule =
        SerializersModule {
            contextual(Instant::class, instantSerializer)
        }

    @Bean
    fun json(serializersModule: SerializersModule): Json =
        Json {
            this.serializersModule = serializersModule
        }

    @Bean
    fun kotlinSerializationHttpMessageConverter(
        json: Json
    ): KotlinSerializationJsonHttpMessageConverter =
        KotlinSerializationJsonHttpMessageConverter(json)
}
