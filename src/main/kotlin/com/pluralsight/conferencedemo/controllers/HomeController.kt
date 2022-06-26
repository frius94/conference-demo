package com.pluralsight.conferencedemo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.JmsException
import org.springframework.jms.core.JmsTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Value("\${app.version}")
    var appVersion: String? = null

    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    @GetMapping
    @RequestMapping("/")
    fun getStatus(): HashMap<String, String> {
        val map: HashMap<String, String> = HashMap()
        map.put("app-version", appVersion!!)
        return map
    }

    @GetMapping("sendAuto")
    @ResponseBody
    fun sendAuto(@RequestParam message: String): String {
        return try {
            jmsTemplate.convertAndSend("DEV.QUEUE.2", message)
            "OK. Saved message: '$message' in queue: DEV.QUEUE.2"
        } catch (e: JmsException) {
            e.printStackTrace()
            "FAIL"
        }
    }
    @GetMapping("sendManual")
    @ResponseBody
    fun sendManual(@RequestParam message: String): String {
        return try {
            jmsTemplate.convertAndSend("DEV.QUEUE.1", message)
            "OK. Saved message: '$message' in queue: DEV.QUEUE.1"
        } catch (e: JmsException) {
            e.printStackTrace()
            "FAIL"
        }
    }

    @GetMapping("receive")
    fun recv(): String {
        return try {
            jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString()
        } catch (e: JmsException) {
            e.printStackTrace()
            "FAIL"
        }
    }
}