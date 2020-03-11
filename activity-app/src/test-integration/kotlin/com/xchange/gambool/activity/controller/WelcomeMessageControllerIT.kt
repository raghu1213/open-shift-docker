package com.xchange.gambool.activity.controller

import com.xchange.gambool.activity.ItTestBase
import com.xchange.gambool.activity.model.WelcomeMessage
import com.xchange.gambool.activity.repository.WelcomeMessageRepository
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.hamcrest.CoreMatchers.`is` as Is

class WelcomeMessageControllerIT : ItTestBase() {
  @Autowired
  lateinit var testRestTemplate: TestRestTemplate
  @Autowired
  lateinit var repository: WelcomeMessageRepository

  @BeforeAll
  fun setup() {
    repository.save(WelcomeMessage(1, "activity", "this is test message"))
  }

  @AfterAll
  fun tearDown() {
    repository.deleteAll()
  }

  @Test
  fun testWelcomeMessage() {
    val result = testRestTemplate.getForEntity("/activity/welcome", WelcomeMessage::class.java)
    Assertions.assertNotNull(result)
    assertEquals(HttpStatus.OK, result.statusCode)
    assertThat(result.body?.message, Is("this is test message"))
    assertThat(result.body?.module, Is("activity"))

  }

}
