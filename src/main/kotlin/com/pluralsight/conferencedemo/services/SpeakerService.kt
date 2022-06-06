package com.pluralsight.conferencedemo.services

import com.pluralsight.conferencedemo.models.Speaker
import org.springframework.stereotype.Service

@Service
class SpeakerService {

    fun concatenateName(speaker: Speaker): String {
        return "${speaker.first_name} ${speaker.last_name}"
    }
}