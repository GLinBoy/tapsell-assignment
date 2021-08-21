package com.glinboy.tapsell.consumer.service

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent

interface EventConsumerApi {
    fun receiveImpressionEvent(event: ImpressionEvent)
    fun receiveClickEvent(event: ClickEvent)
}