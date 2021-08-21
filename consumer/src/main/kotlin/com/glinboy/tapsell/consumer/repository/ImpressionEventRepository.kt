package com.glinboy.tapsell.consumer.repository

import com.glinboy.tapsell.consumer.entity.ImpressionEventModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ImpressionEventRepository: MongoRepository<ImpressionEventModel, String> {
}