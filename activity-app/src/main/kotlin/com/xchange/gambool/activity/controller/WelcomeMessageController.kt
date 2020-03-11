package com.xchange.gambool.activity.controller

import com.xchange.gambool.activity.model.WelcomeMessage
import com.xchange.gambool.activity.service.WelcomeMessageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Api(description ="Setup and Monitoring Controller")
@RequestMapping("/activity")
class WelcomeMessageController(val welcomeMessageService: WelcomeMessageService) {
    @GetMapping("/welcome")
    @ApiOperation(value = "Validates if application is able to connect to database and fetch welcome message",
            notes = "Connects to database")
    @ApiResponses(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 404, message = "Database query did not yield any result")
    )
    fun welcome(): WelcomeMessage {
        return welcomeMessageService.findByModule("activity");
    }
}
