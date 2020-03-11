package com.xchange.gambool.activity.repository

import com.xchange.gambool.activity.ItTestBase
import com.xchange.gambool.activity.model.WelcomeMessage
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.hamcrest.CoreMatchers.`is` as Is


class WelcomeMessageRepositoryIT : ItTestBase() {
  @Autowired
  lateinit var repository: WelcomeMessageRepository

  @AfterEach
  fun cleanUp() {
    repository.deleteAll();
  }

  @Test
  fun welcomeMessageIsSavedUsingRepository() {
    repository.save(WelcomeMessage(1, "activity", "this is test message"))
    val message = repository.findByModule("activity");
    assertThat(message.code, Is(1))
    assertThat(message.message, Is("this is test message"))
  }

  @Test
  fun canFindAllTheSavedMessages() {
    repository.save(WelcomeMessage(1, "activity", "this is activity message"))
    repository.save(WelcomeMessage(2, "test", "this is test message"))
    val messages = repository.findAll();
    assertThat(messages.size, Is(2))
  }

}
