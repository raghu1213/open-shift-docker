package com.xchange.gambool.activity.service

import com.xchange.gambool.activity.model.WelcomeMessage
import com.xchange.gambool.activity.repository.WelcomeMessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WelcomeMessageService(var welcomeMessageRepository: WelcomeMessageRepository) {
    fun findByModule(module: String): WelcomeMessage {
        return welcomeMessageRepository.findByModule(module)
    }

    fun findAll(): List<WelcomeMessage> {
        return welcomeMessageRepository.findAll();

    }

}
