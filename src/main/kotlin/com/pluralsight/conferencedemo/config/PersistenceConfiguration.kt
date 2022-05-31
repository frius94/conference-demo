package com.pluralsight.conferencedemo.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class PersistenceConfiguration {
//    @Bean
//    fun dataSource(): DataSource {
//        val builder = DataSourceBuilder.create()
//        builder.url("jdbc:postgresql://localhost:5432/conference_app")
//        println("My custom datasource bean has been initialized and set")
//        return builder.build()
//    }
}