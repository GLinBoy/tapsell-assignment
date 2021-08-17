package com.glinboy.tapsell.dto

data class ElasticClickEvent(
        val requestId: String,
        val impressionTime: Long,
        val clickTime: Long,
        val adId: String,
        val adTitle: String,
        val advertiserCost: Double,
        val appId: String,
        val appTitle: String
)
