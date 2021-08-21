package com.glinboy.tapsell.producer.controller

import com.glinboy.tapsell.producer.service.GeneratorServiceApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generators")
class GeneratorController(private val generatorServiceApi: GeneratorServiceApi) {

    @PostMapping("start")
    fun startGenerators(@RequestParam(name = "p", required = false, defaultValue = "75") probability: Int): ResponseEntity<Void> {
        generatorServiceApi.startGenerateEvents(probability)
        return ResponseEntity.ok().build()
    }

    @PostMapping("stop")
    fun stopGenerators(): ResponseEntity<Void> {
        generatorServiceApi.stopGenerateEvents()
        return ResponseEntity.ok().build()
    }
}