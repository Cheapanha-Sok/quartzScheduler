package com.example.scheduler.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
@EnableAutoConfiguration
class QuartzConfiguration {

    @QuartzDataSource
    fun quartzDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}