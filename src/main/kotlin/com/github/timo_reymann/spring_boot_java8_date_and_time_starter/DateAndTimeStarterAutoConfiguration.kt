package com.github.timo_reymann.spring_boot_java8_date_and_time_starter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@ComponentScan
open class DateAndTimeStarterAutoConfiguration {
    @Autowired
    var config : DateAndTimeStarterConfiguration? = null

    @Bean
    fun objectMapper(objectMapper: ObjectMapper): ObjectMapper {
        var customizer = JacksonLocalDateAndTimeCustomizer();
        if(config != null) {
            if(!config!!.dateFormat.isNullOrEmpty()) {
                customizer.dateFormat = DateTimeFormatter.ofPattern(config!!.dateFormat)
            }

            if(!config!!.dateTimeFormat.isNullOrEmpty()) {
                customizer.dateTimeFormat = DateTimeFormatter.ofPattern(config!!.dateTimeFormat)
            }
        }

        return customizer.customize(objectMapper)
    }
}