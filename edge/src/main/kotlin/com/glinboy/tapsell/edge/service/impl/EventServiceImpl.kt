package com.glinboy.tapsell.edge.service.impl

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import com.glinboy.tapsell.edge.service.EventServiceApi
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class EventServiceImpl(private val impressionKafkaTemplate: KafkaTemplate<String, ImpressionEvent>,
                       private val clickKafkaTemplate: KafkaTemplate<String, ClickEvent>) : EventServiceApi {

    override fun sendImpressionEvent(event: ImpressionEvent) {
        impressionKafkaTemplate.send("IMPRESSION_TOPIC", event)
    }

    override fun sendClickEvent(event: ClickEvent) {
        clickKafkaTemplate.send("CLICK_TOPIC", event)
    }
}