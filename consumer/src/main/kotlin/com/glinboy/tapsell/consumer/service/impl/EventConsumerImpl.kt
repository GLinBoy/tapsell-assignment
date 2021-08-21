package com.glinboy.tapsell.consumer.service.impl

import com.glinboy.tapsell.consumer.entity.ElasticClickEventModel
import com.glinboy.tapsell.consumer.entity.ImpressionEventModel
import com.glinboy.tapsell.consumer.repository.ElasticClickEventRepository
import com.glinboy.tapsell.consumer.repository.ImpressionEventRepository
import com.glinboy.tapsell.consumer.service.EventConsumerApi
import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class EventConsumerImpl(private val impressionRepository: ImpressionEventRepository,
                        private val elasticClickEventRepository: ElasticClickEventRepository): EventConsumerApi {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["IMPRESSION_TOPIC"], groupId = "EVENTS_GROUPS")
    override fun receiveImpressionEvent(event: ImpressionEvent) {
        logger.info("Impression event received: {}", event.toString())
        val eventModel = ImpressionEventModel(
            timestamp = event.timestamp,
            adId = event.adId,
            adTitle = event.adTitle,
            advertiserCost = event.advertiserCost,
            appId = event.appId,
            appTitle = event.appTitle
        )
        impressionRepository.save(eventModel)
    }

    @KafkaListener(topics = ["CLICK_TOPIC"], groupId = "EVENTS_GROUPS")
    override fun receiveClickEvent(event: ClickEvent) {
        logger.info("Click event received: {}", event.toString())
        val foundById = impressionRepository.findById(event.requestId)
        if(foundById.isPresent) {
            val impressionEventModel = foundById.get()
            logger.info("Impression event found: {}", impressionEventModel)
            val elasticClickEventModel = ElasticClickEventModel(
                requestId = impressionEventModel.requestId,
                impressionTime = impressionEventModel.timestamp,
                clickTime = event.timestamp,
                adId = impressionEventModel.adId,
                adTitle = impressionEventModel.adTitle,
                advertiserCost = impressionEventModel.advertiserCost,
                appId = impressionEventModel.appId,
                appTitle = impressionEventModel.appTitle
            )
            elasticClickEventRepository.save(elasticClickEventModel)
        }
    }
}