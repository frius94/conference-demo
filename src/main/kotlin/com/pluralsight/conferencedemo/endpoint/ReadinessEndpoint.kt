package com.pluralsight.conferencedemo.endpoint

import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@Endpoint(id = "readiness")
class ReadinessEndpoint {
    var ready: String = "NOT_READY"

    @ReadOperation
    fun getReadiness(): String {
        return ready
    }

    @EventListener(ApplicationReadyEvent::class)
    fun setReady() {
        ready = "READY"
    }
}