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
        return sessionRepository.getReferenceById(id)
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
        val existingSession = sessionRepository.getReferenceById(id)
        BeanUtils.copyProperties(session, existingSession, "session_id")
        return sessionRepository.saveAndFlush(existingSession)
    }

//    http://localhost:8080/api/v1/sessions/name?name=access
//    @GetMapping
//    @RequestMapping("name")
//    fun getByName(@RequestParam name: String): Session {
//        return sessionRepository.getReferenceById(2)
////        return sessionRepository.findByName(name)
//    }

//    http://localhost:8080/api/v1/sessions/name/name
//    @GetMapping
//    @RequestMapping("name/{name}")
//    fun getByName(@PathVariable name: String): Session {
//        return sessionRepository.findByName(name)
//    }

//    ----------------------------------------------------------

//        http://localhost:8080/api/v1/sessions/?name=spring
    @GetMapping
    @RequestMapping(value = ["/"], params = ["name"])
    fun getByName(@RequestParam("name") name : String): List<Session> {
    return sessionRepository.findByName(name)
    }

//    @RequestMapping(value="/hello" params= param1)
//    public returnType method(@RequestParam("param1") p) { ... }
//
//    @RequestMapping(value="/hello" params= param2)
//    public differentreturnType method2(@RequestParam("param2") p) { ... }
}
