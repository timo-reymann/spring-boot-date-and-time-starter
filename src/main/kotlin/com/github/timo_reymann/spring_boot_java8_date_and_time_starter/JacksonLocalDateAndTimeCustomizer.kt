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
import java.time.LocalTime
import java.time.format.DateTimeFormatter

open class JacksonLocalDateAndTimeCustomizer {
    var dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var dateTimeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    var timeFormat : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    constructor(configuration: DateAndTimeStarterConfiguration) {
        if (!configuration.dateFormat.isNullOrBlank())
            this.dateFormat = DateTimeFormatter.ofPattern(configuration.dateFormat)

        if (!configuration.dateTimeFormat.isNullOrBlank())
            this.dateTimeFormat = DateTimeFormatter.ofPattern(configuration.dateTimeFormat)

        if(!configuration.timeFormat.isNullOrBlank())
            this.timeFormat = DateTimeFormatter.ofPattern(configuration.timeFormat)
    }

    fun createModule(): SimpleModule {
        val module = SimpleModule("Java8DateAndTimeStarterModule")

        //
        // LocalDate
        //
        module.addSerializer(LocalDate::class.java, object : JsonSerializer<LocalDate>() {
            @Throws(IOException::class, JsonProcessingException::class)
            override fun serialize(value: LocalDate, jgen: JsonGenerator, provider: SerializerProvider) {
                ToStringSerializer.instance.serialize(dateFormat.format(value), jgen, provider)
            }
        })

        module.addDeserializer(LocalDate::class.java, object : JsonDeserializer<LocalDate>() {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate? {
                return LocalDate.parse(p?.text, dateFormat)
            }
        })


        //
        // LocalDateTime
        //
        module.addDeserializer(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime>() {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
                return LocalDateTime.parse(p?.text, dateTimeFormat)
            }
        })

        module.addSerializer(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>() {
            @Throws(IOException::class, JsonProcessingException::class)
            override fun serialize(value: LocalDateTime, jgen: JsonGenerator, provider: SerializerProvider) {
                ToStringSerializer.instance.serialize(dateTimeFormat.format(value), jgen, provider)
            }
        })


        //
        // LocalTime
        //
        module.addSerializer(LocalTime::class.java, object : JsonSerializer<LocalTime>() {
            @Throws(IOException::class, JsonProcessingException::class)
            override fun serialize(value: LocalTime, jgen: JsonGenerator, provider: SerializerProvider) {
                ToStringSerializer.instance.serialize(timeFormat.format(value), jgen, provider)
            }
        })

        module.addDeserializer(LocalTime::class.java, object : JsonDeserializer<LocalTime>() {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalTime? {
                return LocalTime.parse(p?.text, timeFormat)
            }
        })

        return module
    }

    fun customize(objectMapper: ObjectMapper): ObjectMapper {
        objectMapper.registerModule(createModule())
        return objectMapper
    }
}