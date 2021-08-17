package com.glinboy.tapsell.dto

data class ImpressionEvent(
        /**
         * A unique id for the request
         */
        val requestId: String,
        val timestamp: Long,
        val adId: String,
        val adTitle: String,
        val advertiserCost: Double,
        val appId: String,
        val appTitle: String
)
