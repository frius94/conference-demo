package com.pluralsight.conferencedemo.controllers

import com.pluralsight.conferencedemo.models.Session
import com.pluralsight.conferencedemo.repositories.SessionRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/sessions")
class SessionsController {

    @Autowired
    lateinit var sessionRepository: SessionRepository

    @GetMapping
    fun list(): List<Session> {
        return sessionRepository.findAll()
    }

    @GetMapping
    @RequestMapping("{id}")
    fun get(@PathVariable id: Long): Session {
        return sessionRepository.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody session: Session): Session {
        return sessionRepository.saveAndFlush(session)
    }

    @RequestMapping(value = ["{id}"], method = [RequestMethod.DELETE])
    fun delete(@PathVariable id: Long) {
        sessionRepository.deleteById(id)
        return
    }

    @RequestMapping(value = ["{id}"], method = [RequestMethod.PUT])
    fun update(@PathVariable id: Long, @RequestBody session: Session): Session {
        val existingSession = sessionRepository.getById(id)
        BeanUtils.copyProperties(session, existingSession, "session_id")
        return sessionRepository.saveAndFlush(existingSession)
    }
}