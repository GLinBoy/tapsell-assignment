package com.glinboy.tapsell.producer.service.impl

import com.glinboy.tapsell.producer.service.GeneratorServiceApi
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GeneratorServiceImpl:GeneratorServiceApi {

    private var switch: Boolean = false
    private var probability: Int = 75

    private val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedRate = 1000)
    override fun generateEvents() {
        if(switch) {
            logger.info("Run at: {}", LocalDateTime.now().toString())
            if ((1..101).random() <= probability) {
                logger.info("Clicked!")
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