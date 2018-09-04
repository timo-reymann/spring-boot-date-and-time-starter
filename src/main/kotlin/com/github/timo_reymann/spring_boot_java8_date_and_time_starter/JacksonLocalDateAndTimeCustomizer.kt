package com.github.timo_reymann.spring_boot_java8_date_and_time_starter

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class JacksonLocalDateAndTimeCustomizer {
    var dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var dateTimeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    constructor()

    constructor(builder: JacksonLocalDateAndTimeCustomizer.() -> Unit) {
        builder()
    }

    constructor(dateFormat: DateTimeFormatter,
                dateTimeFormat: DateTimeFormatter) {
        this.dateFormat = dateFormat
        this.dateTimeFormat = dateTimeFormat
    }

    constructor(dateFormat: String, dateTimeFormat: String) {
        this.dateFormat = DateTimeFormatter.ofPattern(dateFormat)
        this.dateTimeFormat = DateTimeFormatter.ofPattern(dateTimeFormat)
    }

    fun createModule(): SimpleModule {
        val module = SimpleModule("Java8DateAndTimeStarterModule")

        module.addSerializer(LocalDate::class.java, object : JsonSerializer<LocalDate>() {
            @Throws(IOException::class, JsonProcessingException::class)
            override fun serialize(value: LocalDate, jgen: JsonGenerator, provider: SerializerProvider) {
                ToStringSerializer.instance.serialize(dateFormat.format(value), jgen, provider)
            }
        })

        module.addSerializer(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>() {
            @Throws(IOException::class, JsonProcessingException::class)
            override fun serialize(value: LocalDateTime, jgen: JsonGenerator, provider: SerializerProvider) {
                ToStringSerializer.instance.serialize(dateTimeFormat.format(value), jgen, provider)
            }
        })

        module.addDeserializer(LocalDate::class.java, object : JsonDeserializer<LocalDate>() {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate? {
                return LocalDate.parse(p?.text, dateFormat)
            }
        })

        module.addDeserializer(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime>() {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
                return LocalDateTime.parse(p?.text, dateTimeFormat)
            }
        })
        return module
    }

    fun customize(objectMapper: ObjectMapper): ObjectMapper {
        objectMapper.registerModule(createModule())
        return objectMapper
    }
}