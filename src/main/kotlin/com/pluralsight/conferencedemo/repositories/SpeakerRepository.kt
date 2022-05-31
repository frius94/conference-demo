package com.pluralsight.conferencedemo.repositories

import com.pluralsight.conferencedemo.models.Speaker
import org.springframework.data.jpa.repository.JpaRepository

interface SpeakerRepository: JpaRepository<Speaker, Long> {

}