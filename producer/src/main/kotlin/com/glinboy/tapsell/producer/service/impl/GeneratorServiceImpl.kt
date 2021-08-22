package com.glinboy.tapsell.producer.service.impl

import com.github.javafaker.Faker
import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import com.glinboy.tapsell.producer.service.GeneratorServiceApi
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Service
class GeneratorServiceImpl : GeneratorServiceApi {

    private var switch: Boolean = false
    private var probability: Int = 75

    private val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedRate = 1000)
    override fun generateEvents() {
        if (switch) {
            val impressionEvent = ImpressionEvent(
                requestId = "0",
                timestamp = Instant.now().epochSecond,
                adId = UUID.randomUUID().toString(),
                adTitle = Faker.instance().funnyName().name(),
                advertiserCost = 0.0,
                appId = UUID.randomUUID().toString(),
                appTitle = Faker.instance().name().title()
            )
            logger.info("Impression Event: {}", impressionEvent)
            if ((1..101).random() <= probability) {
                val clickEvent = ClickEvent(
                    requestId = impressionEvent.requestId,
                    timestamp = Instant.now().epochSecond
                )
                logger.info("Click Event: {}", clickEvent)
            }
        }
    }

    override fun startGenerateEvents(probability: Int) {
        switch = true
        this.probability = probability
    }

    override fun stopGenerateEvents() {
        switch = false
    }
}