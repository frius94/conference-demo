package com.pluralsight.conferencedemo.components

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.actuate.health.Status

class MaxMemoryHealthIndicator : HealthIndicator {
    override fun health(): Health {
        val invalid = Runtime.getRuntime().maxMemory() < (100 * 1024 * 1024)
        val status = if (invalid) Status.DOWN else Status.UP
        return Health.status(status).build()
    }
}