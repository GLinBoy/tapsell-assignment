package com.glinboy.tapsell.edge.controller

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/event")
class EventController {

    @PostMapping("/impression")
    fun impressionEventHandler(event: ImpressionEvent): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }

    @PostMapping("/click")
    fun clickEventHandler(event: ClickEvent): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }
}