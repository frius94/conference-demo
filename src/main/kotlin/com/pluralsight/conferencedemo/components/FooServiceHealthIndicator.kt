package com.pluralsight.conferencedemo.components

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class FooServiceHealthIndicator : HealthIndicator {
    override fun health(): Health {
        /*perform a custom health check
         inspect the status
         */
        return Health.up().build()
//        return Health.down().build()
//        return Health.down()
//            .withDetail("response_code", "...")
//            .withDetail("response_ms", "...")
//            .build()
    }
}