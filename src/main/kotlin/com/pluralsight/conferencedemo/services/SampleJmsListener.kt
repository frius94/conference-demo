package com.pluralsight.conferencedemo.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service
import javax.jms.Message

@Service
class SampleJmsListener {

    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    @JmsListener(destination = "DEV.QUEUE.2")
    fun sampleJmsListenerMethod(order: Message) {
        println(jmsTemplate.receiveAndConvert("DEV.QUEUE.2").toString())
    }
}