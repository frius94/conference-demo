package com.pluralsight.conferencedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jms.annotation.EnableJms

//@SpringBootApplication(exclude = [ SecurityAutoConfiguration::class, ManagementWebSecurityAutoConfiguration::class ])
@SpringBootApplication
@EnableJms
class ConferenceDemoApplication

fun main(args: Array<String>) {
	runApplication<ConferenceDemoApplication>(*args)
}
