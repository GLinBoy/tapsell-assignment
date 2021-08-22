package com.glinboy.tapsell.edge.service

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent

interface EventServiceApi {
    fun sendImpressionEvent(event: ImpressionEvent): ImpressionEvent
    fun sendClickEvent(event: ClickEvent)
}