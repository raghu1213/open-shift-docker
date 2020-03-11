package com.xchange.gambool.activity.repository

import com.xchange.gambool.activity.model.Tag
import org.springframework.data.mongodb.repository.MongoRepository

interface TaggingRepository : MongoRepository<Tag, Int> {
  fun findByIsActive(isActive: Boolean = true): List<Tag>
  fun findByTagIdAndIsActive(tagId: Int, isActive: Boolean = true): Tag
  fun findByTagId(tagId: Int): Tag
}
