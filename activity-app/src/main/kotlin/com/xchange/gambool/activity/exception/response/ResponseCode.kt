package com.xchange.gambool.activity.exception.response

import org.springframework.http.HttpStatus

enum class ResponseCode(val VALUE: HttpStatus) {
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND)
}