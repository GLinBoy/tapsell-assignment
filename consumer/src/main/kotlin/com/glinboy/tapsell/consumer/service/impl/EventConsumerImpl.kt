package com.glinboy.tapsell.consumer.service.impl

import com.glinboy.tapsell.consumer.entity.ImpressionEventModel
import com.glinboy.tapsell.consumer.repository.ImpressionEventRepository
import com.glinboy.tapsell.consumer.service.EventConsumerApi
import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class EventConsumerImpl(private val impressionRepository: ImpressionEventRepository): EventConsumerApi {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["IMPRESSION_TOPIC"], groupId = "EVENTS_GROUPS")
    override fun receiveImpressionEvent(event: ImpressionEvent) {
        logger.info("Impression event received: {}", event.toString())
    }

    @KafkaListener(topics = ["CLICK_TOPIC"], groupId = "EVENTS_GROUPS")
    override fun receiveClickEvent(event: ClickEvent) {
        logger.info("Click event received: {}", event.toString())
    }
}