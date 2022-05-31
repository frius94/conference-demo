package com.pluralsight.conferencedemo.controllers

import com.pluralsight.conferencedemo.models.Speaker
import com.pluralsight.conferencedemo.repositories.SpeakerRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/speakers")
class SpeakersController {

    @Autowired
    lateinit var speakerRepository: SpeakerRepository

    @GetMapping
    fun list(): List<Speaker> {
        return speakerRepository.findAll()
    }

    @GetMapping
    @RequestMapping("{id}")
    fun get(@PathVariable id: Long): Speaker {
        return speakerRepository.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody speaker: Speaker): Speaker {
        return speakerRepository.saveAndFlush(speaker)
    }

    @RequestMapping(value = ["{id}"], method = [RequestMethod.DELETE])
    fun delete(@PathVariable id: Long) {
        speakerRepository.deleteById(id)
    }

    @RequestMapping(value = ["{id}"], method = [RequestMethod.PUT])
    fun update(@PathVariable id: Long, @RequestBody speaker: Speaker): Speaker {
        val existingSpeaker = speakerRepository.getById(id)
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id")
        return speakerRepository.saveAndFlush(existingSpeaker)
    }
}