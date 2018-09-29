package com.github.timo_reymann.spring_boot_java8_date_and_time_starter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@ComponentScan
open class DateAndTimeStarterAutoConfiguration {
    @Autowired
    var config: DateAndTimeStarterConfiguration? = null

    @Bean
    fun configure(): Jackson2ObjectMapperBuilder {
        val builder = Jackson2ObjectMapperBuilder()
        builder.modulesToInstall(JacksonLocalDateAndTimeCustomizer(this.config!!).createModule())
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        return builder
    }
}