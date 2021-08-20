package com.glinboy.tapsell.edge.controller

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import com.glinboy.tapsell.edge.service.EventServiceApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/events")
class EventController(private val eventService: EventServiceApi) {

    @PostMapping("/impressions")
    fun impressionEventHandler(@Valid @RequestBody event: ImpressionEvent): ResponseEntity<Void> {
        eventService.sendImpressionEvent(event)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/clicks")
    fun clickEventHandler(@Valid @RequestBody event: ClickEvent): ResponseEntity<Void> {
        eventService.sendClickEvent(event)
        return ResponseEntity.ok().build()
    }
}