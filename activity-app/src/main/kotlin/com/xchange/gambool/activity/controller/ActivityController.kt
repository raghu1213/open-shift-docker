package com.xchange.gambool.activity.controller

import com.xchange.gambool.activity.model.Activity
import com.xchange.gambool.activity.service.ActivityService
import io.swagger.annotations.*

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@Api(description = "Activity Controller")
@RequestMapping("activity")
class ActivityController(var activityService: ActivityService) {
  var logger: Logger = LoggerFactory.getLogger(ActivityController::class.java)

  /**
   * This endpoint gets all the available activities
   * Filters out any row that is marked as inactive
   */
  @GetMapping
  @ApiOperation(value = "Gets all available activities",
    notes = "Fetches from database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "No records found")
  )
  fun getAll(): List<Activity> {
    try {
      logger.info("Request to get all the activities received...")
      return activityService.findAll()
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("getAll request completed.")

    }

  }

  /**
   * This enpoint inserts a new activity into the database
   */
  @PostMapping
  @ApiOperation(value = "Creates a new activity",
    notes = "Saves in database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK")
  )
  fun createNewActivity(@RequestBody activity: Activity): Activity {
    try {
      logger.info("Request to create new activity received...")
      return activityService.createNew(activity)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("Create new activity request completed.")

    }
  }

  /**
   * This endpoint gets all the activities where name matches the <code>name</code>
   */
  @GetMapping("/name/{name}")
  @ApiOperation(value = "Gets all available activities by name",
    notes = "Fetches from database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "No records found")
  )
  @ApiParam(name = "name", value = "Name of the tag", required = true, type = "String")
  fun getByName(@PathVariable name: String): List<Activity> {
    try {
      logger.info("Request to find activity by name received...")
      return activityService.getByName(name)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("getByName completed.")
    }
  }

  @GetMapping("/id/{id}")
  @ApiOperation(value = "Gets an Activity by id",
    notes = "Fetches from database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "No records found")
  )
  @ApiParam(name = "id", value = "unique activity id", required = true, type = "Int")
  fun getById(@PathVariable id: Int): Activity {
    try {
      logger.info("Request to find activity by id received...")
      return activityService.getById(id)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("getById completed.")
    }
  }


  @PutMapping
  @ApiOperation(value = "Updates and activity by its id",
    notes = "Fetches from database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "No records found for the given activity id")
  )
  fun updateActivityById(@RequestBody activity: Activity): Activity {
    try {
      logger.info("Request to update activity received...")
      return activityService.update(activity)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("updateActivityById completed.")
    }
  }

  @GetMapping("/tag/{tagIds}")
  @ApiOperation(value = "Gets all available activities by a tag id",
    notes = "Fetches from database")
  @ApiResponses(
    ApiResponse(code = 200, message = "OK"),
    ApiResponse(code = 404, message = "No records found")
  )
  @ApiParam(name = "tagId", value = "tag id", required = true, type = "List<Int>")
  fun getByTagId(@PathVariable tagIds: List<Int>): List<Activity> {
    try {
      logger.info("Request to get activities by tag...")
      return activityService.getByTagId(tagIds)
    } catch (ex: Exception) {
      logger.error("Error executing request", ex)
      throw ex
    } finally {
      logger.info("getByTagId completed.")
    }
  }


}
