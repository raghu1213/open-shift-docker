package com.xchange.gambool.activity.controller

import com.xchange.gambool.activity.ItTestBase
import com.xchange.gambool.activity.exception.ActivityErrorResponse
import com.xchange.gambool.activity.model.Activity
import com.xchange.gambool.activity.repository.ActivityRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.hamcrest.CoreMatchers.`is` as Is

internal class ActivityControllerIT : ItTestBase() {
  @Autowired
  lateinit var testRestTemplate: TestRestTemplate
  @Autowired
  lateinit var repository: ActivityRepository

  @BeforeEach
  fun setup() {
    insertTestData()
  }

  @Test
  fun `test To Check GetAll OnlyF etches Active Records`() {
    assertThat(repository.findAll().size, Is(4)) // just make sure correct test data is provided

    val result = testRestTemplate.getForEntity("/activity", Array<Activity>::class.java)
    val body = result.body
    assertNotNull(result);
    assertThat(result.statusCode, Is(HttpStatus.OK))
    assertThat(body?.size, Is(3))
  }

  @Test
  fun `test To Check GetAll Records When Nothing Is Inactive`() {
    insertTestData(false)
    assertThat(repository.findAll().size, Is(3)) // just make sure correct test data is provided

    val result = testRestTemplate.getForEntity("/activity", Array<Activity>::class.java)
    val body = result.body
    assertNotNull(result);
    assertThat(result.statusCode, Is(HttpStatus.OK))
    assertThat(body?.size, Is(3))
  }

  @Test
  fun `test Creation Of New Activity`() {
    val activity = Activity(11, "Badminton", intArrayOf(1), "description", true)
    val result = testRestTemplate.postForEntity("/activity", activity, Activity::class.java)
    assertThat(result.body, CoreMatchers.`is`(equalTo(activity)));
    assertThat(repository.findAll().size, CoreMatchers.`is`(5))
  }


  @Test
  fun `test FindByName`() {
    val result = testRestTemplate.getForEntity("/activity/name/Swimming", Array<Activity>::class.java)
    assertNotNull(result.body)
    assertThat(result.body?.size, CoreMatchers.`is`(1))
    assertNotNull(result.body?.get(0))
    assertThat(result.body?.get(0)?.name, CoreMatchers.`is`("Swimming"))
  }

  @Test
  fun `test FindByName Does Not Include Inactive Records`() {
    val result = testRestTemplate.getForEntity("/activity/name/Beedminton", Array<Activity>::class.java)
    assertNotNull(result.body)
    assertThat(result.body?.size, CoreMatchers.`is`(0))
  }

  @Test
  fun `when GetById Is Requested For Valid Id Repose Returns Valid Activity`() {
    val result = testRestTemplate.getForEntity("/activity/id/1", Activity::class.java)
    assertNotNull(result.body)
    assertThat(result.statusCode, CoreMatchers.`is`(HttpStatus.OK))
    assertThat(result.body?.name, CoreMatchers.`is`("Badminton"))
  }

  @Test
  fun `when GetById Is Requested For Non Existing Id Repose Returns 404NotFound`() {
    val result = testRestTemplate.getForEntity("/activity/id/404", ActivityErrorResponse::class.java)
    assertNotNull(result.body)
    assertThat(result.statusCode, CoreMatchers.`is`(HttpStatus.NOT_FOUND))
  }

  @Test
  fun `when Update Is Called For Valid Id Update Is Executed Without Creating Duplicate`() {
    val activity = Activity(1, "Badminton", intArrayOf(1), "This is badminton", true)
    val originalValue = repository.findByActivityIdAndIsActive(1, true)
    assertThat(originalValue.description, CoreMatchers.`is`("description"))

    testRestTemplate.put("/activity", activity)

    val result = repository.findByActivityIdAndIsActive(1, true)
    assertThat(result.description, CoreMatchers.`is`("This is badminton"))
    assertThat(repository.findAll().size, CoreMatchers.`is`(4))

  }

  @Test
  fun `when Update Is Called With Invalid Activity Then It Throws Error`() {
    val activity = Activity(404, "changed", intArrayOf(1), "This is badminton", true)
    testRestTemplate.put("/activity", activity)
    assertThat(repository.findAll().size, CoreMatchers.`is`(4))

    val searchResult = repository.findByNameLikeAndIsActive("changed")
    assertTrue(searchResult.isEmpty())

  }


  @Test
  fun `can Fetch Activities By Tag`() {
    var result = testRestTemplate.getForEntity("/activity/tag/1,2", Array<Activity>::class.java)
    assertThat(result.body?.size, CoreMatchers.`is`(3))

    result = testRestTemplate.getForEntity("/activity/tag/2", Array<Activity>::class.java)
    assertThat(result.body?.size, CoreMatchers.`is`(1))
  }

  @Test
  fun `when Tag Is Not Present Then Gives 404Error`() {
    val result = testRestTemplate.getForEntity("/activity/tag/404", ActivityErrorResponse::class.java)
    assertThat(result.statusCode, CoreMatchers.`is`(HttpStatus.NOT_FOUND))
  }

  private fun insertTestData(insertInactive: Boolean = true) {
    repository.deleteAll()
    repository.save(Activity(1, "Badminton", intArrayOf(1), "description", true))
    repository.save(Activity(2, "Swimming", intArrayOf(1), "description", true))
    if (insertInactive) {
      repository.save(Activity(3, "Beedminton", intArrayOf(1), "Description", false))
    }
    repository.save(Activity(4, "Tennis", intArrayOf(2), "Description", true))
  }
}
