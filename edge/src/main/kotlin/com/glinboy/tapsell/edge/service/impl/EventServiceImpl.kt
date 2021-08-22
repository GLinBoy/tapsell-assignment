package com.glinboy.tapsell.edge.service.impl

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import com.glinboy.tapsell.edge.service.EventServiceApi
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventServiceImpl(private val impressionKafkaTemplate: KafkaTemplate<String, ImpressionEvent>,
                       private val clickKafkaTemplate: KafkaTemplate<String, ClickEvent>) : EventServiceApi {

    override fun sendImpressionEvent(event: ImpressionEvent): ImpressionEvent {
        val ev = ImpressionEvent(
            requestId = UUID.randomUUID().toString(),
            timestamp = event.timestamp,
            adId = event.adId,
            adTitle = event.adTitle,
            advertiserCost = event.advertiserCost,
            appId = event.appId,
            appTitle = event.appTitle
        )
        impressionKafkaTemplate.send("IMPRESSION_TOPIC", ev)
        return ev
    }

    override fun sendClickEvent(event: ClickEvent) {
        clickKafkaTemplate.send("CLICK_TOPIC", event)
    }
}