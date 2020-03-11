package com.xchange.gambool.activity.controller

import com.xchange.gambool.activity.ItTestBase
import com.xchange.gambool.activity.model.Tag
import com.xchange.gambool.activity.repository.TaggingRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus

internal class TaggingControllerIT : ItTestBase() {
  @Autowired
  lateinit var testRestTemplate: TestRestTemplate

  @Autowired
  lateinit var repository: TaggingRepository

  @BeforeEach
  fun setup() {
    insertTestData()
  }

  @Test
  fun `Requesting all the tags will fetch all active tags`() {
    assertEquals(4, repository.findAll().size) // just to make sure setup is correct

    val result = testRestTemplate.getForEntity("/tag", Array<Tag>::class.java)

    assertEquals(HttpStatus.OK, result.statusCode)
    assertThat(result.body?.size, CoreMatchers.`is`(3)) // should give only active records

  }

  @Test
  fun `Test creation of new tag`() {
    val newTag = Tag(5, "test", true)
    val result = testRestTemplate.postForEntity("/tag", newTag, Tag::class.java)
    assertEquals(newTag, result.body)
    assertThat(repository.findByIsActive().size, CoreMatchers.`is`(4))
  }

  @Test
  fun `Test to update existing tag`() {
    //validate existing data
    val existingTag = repository.findByTagId(4)
    assertEquals(4,existingTag.tagId)
    assertEquals("Music",existingTag.name)
    //action
    val updatedTag = Tag(4, "test", false)
    testRestTemplate.put("/tag", updatedTag)

    //verify
    val result = repository.findByTagId(4)
    assertThat(result.name,CoreMatchers.`is`("test"))
  }

  @Test
  fun `When invalid tag id is requested for update it throws error`(){
    val updatedTag = Tag(404, "test", false)
    testRestTemplate.put("/tag", updatedTag)

   assertThrows(EmptyResultDataAccessException::class.java){ repository.findByTagId(404)}

 }

  private fun insertTestData(insertInactive: Boolean = true) {
    repository.deleteAll()
    repository.save(Tag(1, "Outdoor", true))
    repository.save(Tag(2, "Water", true))
    if (insertInactive) {
      repository.save(Tag(3, "Random", false))
    }
    repository.save(Tag(4, "Music", true))
  }
}
