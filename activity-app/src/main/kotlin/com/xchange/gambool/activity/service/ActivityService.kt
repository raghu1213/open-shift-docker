package com.xchange.gambool.activity.service

import com.xchange.gambool.activity.model.Activity
import com.xchange.gambool.activity.repository.ActivityRepository
import org.bson.BsonTimestamp
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class ActivityService(var activityRepository: ActivityRepository) {
  val logger: Logger = LoggerFactory.getLogger(ActivityService::class.java)
  /**
   * finds all the activities
   */
  fun findAll(): List<Activity> {
    return activityRepository.findByIsActive(true)
  }

  fun createNew(activity: Activity): Activity {
    return activityRepository.insert(activity)
  }

  fun getByName(name: String): List<Activity> {
    return activityRepository.findByNameLikeAndIsActive(name, true)
  }

  fun getById(id: Int): Activity {
      return activityRepository.findByActivityIdAndIsActive(id)
    }

  fun update(activity: Activity): Activity {
    try {
      val existing = getById(activity.activityId)
      return activityRepository.save(
        activity
          .withId(existing.id)
          .withModifiedDate(BsonTimestamp(System.nanoTime()))
      )

    } catch (ex: EmptyResultDataAccessException) {
      logger.error("Invalid id {} provided to update activity", activity.activityId)
      throw ex
    }
  }

  fun getByTagId(tagIds: List<Int>): List<Activity> {
    val resultByTags = activityRepository.findByTagIdsInAndIsActive(tagIds)
    if (resultByTags.isEmpty()) {
      throw EmptyResultDataAccessException("No activity found by this tag id", 1)
    }
    return resultByTags

  }


}
