package com.xchange.gambool.activity.service

import com.xchange.gambool.activity.repository.ActivityRepository
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class ActivityServiceTest {

  @Mock
  lateinit var activityRepository: ActivityRepository

    @Test
    fun `when Tag Is Not Present It Throws Exception`() {
      val activityService = ActivityService(activityRepository)
      Mockito.`when`(activityRepository.findByTagIdsInAndIsActive(listOf(1,2))).thenReturn(emptyList())
      assertThrows(EmptyResultDataAccessException::class.java){
        activityService.getByTagId(listOf(1,2))
      }
      Mockito.verify(activityRepository,times(1)).findByTagIdsInAndIsActive(listOf(1,2))
    }
}
