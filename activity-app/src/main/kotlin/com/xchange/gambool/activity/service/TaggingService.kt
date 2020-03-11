package com.xchange.gambool.activity.service

import com.xchange.gambool.activity.model.Tag
import com.xchange.gambool.activity.repository.TaggingRepository
import org.bson.BsonTimestamp
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class TaggingService(var taggingRepository: TaggingRepository) {
  val logger: Logger = LoggerFactory.getLogger(TaggingService::class.java)
  fun findAll(): List<Tag> {
    return taggingRepository.findByIsActive()
  }

  fun createNew(tag: Tag): Tag {
    return taggingRepository.insert(tag)
  }

  fun update( tag: Tag): Tag {
    try {
      val existing = taggingRepository.findByTagId(tag.tagId)
      return taggingRepository.save(
        tag.withId(existing.id)
        .withModifiedDate(BsonTimestamp(System.nanoTime())
        )
      )

    } catch (ex: EmptyResultDataAccessException) {
      logger.error("Invalid id {} provided to update Tag", tag.tagId)
      throw ex
    }
  }

}
