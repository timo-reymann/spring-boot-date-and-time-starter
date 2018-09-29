package com.github.timo_reymann.spring_boot_java8_date_and_time_starter

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("jackson.date-and-time")
@EnableConfigurationProperties
open class DateAndTimeStarterConfiguration {
    /**
     * Format for date serialize and deserialize
     */
    var dateFormat: String? = null

    /**
     * DateTime format for date serialize and deserialize
     */
    var dateTimeFormat: String? = null

    /**
     * Time format for serialize and deserialize
     */
    var timeFormat : String? = null
}