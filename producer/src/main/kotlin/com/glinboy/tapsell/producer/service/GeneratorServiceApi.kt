package com.glinboy.tapsell.producer.service

interface GeneratorServiceApi {
    fun generateEvents()
    fun startGenerateEvents(probability: Int)
    fun stopGenerateEvents()
}