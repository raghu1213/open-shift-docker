package com.xchange.gambool.activity.exception.handler

import com.xchange.gambool.activity.exception.ActivityErrorResponse
import com.xchange.gambool.activity.exception.response.ResponseCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Application exception handler
 * To avoid to much exception details to the clients of our api
 */
@ControllerAdvice
class ActivityServiceExceptionHandler : ResponseEntityExceptionHandler() {
  @ExceptionHandler(value = [(EmptyResultDataAccessException::class)])
  fun dataNotFoundException(ex: EmptyResultDataAccessException, request: WebRequest): ResponseEntity<ActivityErrorResponse> {
    this.logger.error(ex.message)
    return ResponseEntity(ActivityErrorResponse("backend", ex.message), ResponseCode.DATA_NOT_FOUND.VALUE)
  }
}
