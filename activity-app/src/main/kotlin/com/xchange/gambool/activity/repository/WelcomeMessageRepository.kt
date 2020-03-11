package com.xchange.gambool.activity.repository

import com.xchange.gambool.activity.model.WelcomeMessage
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface WelcomeMessageRepository : MongoRepository<WelcomeMessage, Int> {
    /**
     * finds message by code
     */
    fun findByModule(module: String):WelcomeMessage
}