package com.xchange.gambool.activity.repository

import com.xchange.gambool.activity.model.Activity
import org.springframework.data.mongodb.repository.MongoRepository

interface ActivityRepository : MongoRepository<Activity, Int> {
  /**
   * finds active records only
   */
  fun findByIsActive(isActive: Boolean = true): List<Activity>

  fun findByNameLikeAndIsActive(name: String,isActive: Boolean = true): List<Activity>

  fun findByActivityIdAndIsActive(activityId: Int, isActive: Boolean = true): Activity

  fun findByTagIdsInAndIsActive(tagIds: List<Int>, isActive: Boolean = true): List<Activity>

}
