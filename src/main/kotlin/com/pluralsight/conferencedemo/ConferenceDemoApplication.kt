package com.pluralsight.conferencedemo

import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

//@SpringBootApplication(exclude = [ SecurityAutoConfiguration::class, ManagementWebSecurityAutoConfiguration::class ])
@SpringBootApplication
class ConferenceDemoApplication

fun main(args: Array<String>) {
	runApplication<ConferenceDemoApplication>(*args)
}
