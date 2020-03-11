package com.xchange.gambool.activity.controller

import com.xchange.gambool.activity.service.TaggingService
import io.swagger.annotations.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

private typealias Tag = com.xchange.gambool.activity.model.Tag

@RestController
@Api(description = "Tagging Controller")
@RequestMapping("/tag")
class TaggingController(var taggingService: TaggingService) {
  var logger: Logger = LoggerFactory.getLogger(TaggingController::class.java)

  @GetMapping
  @ApiOperation(value = "Gets all available tags",
    notes = "Fetches from database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "No records found")
  )
  fun getAll(): List<Tag> {
    try {
      logger.info("Request to find all the tags received...")
      return taggingService.findAll()
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("getAll request completed.")
    }
  }

  @PostMapping
  @ApiOperation(value = "creates a new tag",
    notes = "Saves in DB")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK")
  )
  fun createNewTag(@RequestBody tag: Tag): Tag {
    try {
      logger.info("Request to create new tag received...")
      return taggingService.createNew(tag)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("createNewTag request completed.")

    }
  }

  @PutMapping
  @ApiOperation(value = "updates an existing tag",
    notes = "Saves in DB")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "tag id not found")
  )
  fun updateATag( @RequestBody tag: Tag): Tag {
    try {
      logger.info("Request to create new tag received...")
      return taggingService.update(tag)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("createNewTag request completed.")

    }
  }

}
