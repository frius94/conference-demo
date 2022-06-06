package com.pluralsight.conferencedemo.controllers

import com.pluralsight.conferencedemo.models.Speaker
import com.pluralsight.conferencedemo.repositories.SpeakerRepository
import com.pluralsight.conferencedemo.services.SpeakerService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/speakers")
class SpeakersController {

    @Autowired
    lateinit var speakerRepository: SpeakerRepository
    @Autowired
    lateinit var speakerService: SpeakerService

    @GetMapping
    fun list(): List<Speaker> {
        return speakerRepository.findAll()
    }

    @GetMapping
    @RequestMapping("{id}")
    fun get(@PathVariable id: Long): Speaker {
        return speakerRepository.getReferenceById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody speaker: Speaker): Speaker {
        return speakerRepository.saveAndFlush(speaker)
    }

//    @RequestMapping(value = ["{id}"], method = [RequestMethod.DELETE])
    @DeleteMapping(value = ["{id}"])
    fun delete(@PathVariable id: Long) {
        speakerRepository.deleteById(id)
    }

//    @RequestMapping(value = ["{id}"], method = [RequestMethod.PUT])
    @PutMapping(value = ["{id}"])
    fun update(@PathVariable id: Long, @RequestBody speaker: Speaker): Speaker {
        val existingSpeaker = speakerRepository.getReferenceById(id)
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id")
        return speakerRepository.saveAndFlush(existingSpeaker)
    }

    @GetMapping
    @RequestMapping(params = ["firstname"])
    fun getByFirstName(@RequestParam("firstname") firstname : String): List<Speaker> {
        return speakerRepository.findByFirstName(firstname)
    }

    @GetMapping
    @RequestMapping(params = ["lastname"])
    fun getByLastName(@RequestParam("lastname") lastname : String): List<Speaker> {
        return speakerRepository.findByLastName(lastname)
    }

    @GetMapping
    @RequestMapping(params = ["title"])
    fun getByTitle(@RequestParam("title") title : String): List<Speaker> {
        return speakerRepository.findByTitle(title)
    }

    @GetMapping
    @RequestMapping(params = ["company"])
    fun getByCompany(@RequestParam("company") company : String): List<Speaker> {
        return speakerRepository.findByCompany(company)
    }

    @GetMapping
    @RequestMapping(params = ["name"])
    fun getByName(@RequestParam("name") name : String): ArrayList<String> {
        val firstnames = speakerRepository.findByFirstName(name)
        val lastnames = speakerRepository.findByLastName(name)
        val result = ArrayList<String>()
        for (speaker in firstnames) {
            result.add(speakerService.concatenateName(speaker))
        }
        for (speaker in lastnames) {
            result.add(speakerService.concatenateName(speaker))
        }
        return result
    }
}