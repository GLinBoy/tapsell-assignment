package com.glinboy.tapsell.edge.controller

import com.glinboy.tapsell.dto.ClickEvent
import com.glinboy.tapsell.dto.ImpressionEvent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/event")
class EventController {

    @PostMapping("/impression")
    fun impressionEventHandler(@Valid @RequestBody event: ImpressionEvent): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }

    @PostMapping("/click")
    fun clickEventHandler(@Valid @RequestBody event: ClickEvent): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }
}