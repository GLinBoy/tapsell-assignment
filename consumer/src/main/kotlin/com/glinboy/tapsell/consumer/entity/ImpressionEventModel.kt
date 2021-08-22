package com.glinboy.tapsell.consumer.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ImpressionEventModel(
    @Id
    val requestId: String,
    val timestamp: Long,
    val adId: String,
    val adTitle: String,
    val advertiserCost: Double,
    val appId: String,
    val appTitle: String
)
