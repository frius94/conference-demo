package com.pluralsight.conferencedemo.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Value("\${app.version}")
    var appVersion: String? = null


    @GetMapping
    @RequestMapping("/")
    fun getStatus(): HashMap<String, String> {
        val map: HashMap<String, String> = HashMap()
        map.put("app-version", appVersion!!)
        return map
    }
}