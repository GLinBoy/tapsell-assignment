package com.glinboy.tapsell.consumer.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "click-event-index")
data class ElasticClickEventModel(
        @Id
        val requestId: String,
        val impressionTime: Long,
        val clickTime: Long,
        val adId: String,
        val adTitle: String,
        val advertiserCost: Double,
        val appId: String,
        val appTitle: String
)
