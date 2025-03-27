package com.crispinlab.article.config

import java.nio.charset.StandardCharsets
import java.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@TestConfiguration
class ControllerTestConfig {
    @Bean
    fun kotlinSerializationJson(serializersModule: SerializersModule): Json =
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            this.serializersModule = serializersModule
        }

    @Bean
    fun mockMvc(
        webApplicationContext: WebApplicationContext,
        stringHttpMessageConverter: StringHttpMessageConverter
    ): MockMvc =
        MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(
                CharacterEncodingFilter(
                    StandardCharsets.UTF_8.name(),
                    true
                )
            ).defaultRequest<DefaultMockMvcBuilder>(
                MockMvcRequestBuilders
                    .get("/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8.name())
            ).build()

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
    fun kotlinSerializationHttpMessageConverter(
        json: Json
    ): KotlinSerializationJsonHttpMessageConverter =
        KotlinSerializationJsonHttpMessageConverter(json)
}
