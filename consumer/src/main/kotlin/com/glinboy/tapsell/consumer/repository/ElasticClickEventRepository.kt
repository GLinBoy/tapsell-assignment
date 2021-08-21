package com.glinboy.tapsell.consumer.repository

import com.glinboy.tapsell.consumer.entity.ElasticClickEventModel
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ElasticClickEventRepository: ElasticsearchRepository<ElasticClickEventModel, String> {
}