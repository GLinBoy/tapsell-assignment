package com.glinboy.tapsell.producer.service.impl

import com.github.javafaker.Faker
import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import com.glinboy.tapsell.producer.service.GeneratorServiceApi
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.util.UriComponentsBuilder
import java.time.Instant
import java.util.*

@Service
class GeneratorServiceImpl() : GeneratorServiceApi {

    private var switch: Boolean = false
    private var probability: Int = 75

    private val logger = LoggerFactory.getLogger(javaClass)
    private val restTemplate = RestTemplateBuilder().build()

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
            var iv: ImpressionEvent? = null
            try {
                iv = restTemplate.postForObject(
                    UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8081/events/impressions")
                        .build()
                        .toUri(),
                    impressionEvent,
                    ImpressionEvent::class.java
                )
            } catch (e: RestClientResponseException) {
                logger.error("Calling ImpressionEvent API failed: {}", e.message)
            }
            if ((1..101).random() <= probability && iv != null && iv.requestId != "0") {
                val clickEvent = ClickEvent(
                    requestId = iv.requestId,
                    timestamp = Instant.now().epochSecond
                )
                logger.info("Click Event: {}", clickEvent)
                try {
                    restTemplate.postForObject(
                        UriComponentsBuilder
                            .fromHttpUrl("http://localhost:8081/events/clicks")
                            .build()
                            .toUri(),
                        clickEvent,
                        Void::class.java
                    )
                } catch (e: RestClientResponseException) {
                    logger.error("Calling Click API failed: {}", e.message)
                }
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