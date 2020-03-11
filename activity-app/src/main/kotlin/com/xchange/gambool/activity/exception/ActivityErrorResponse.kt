package com.xchange.gambool.activity.exception

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Generic exception response
 */
data class ActivityErrorResponse @JsonCreator constructor (
  @JsonProperty("source") val source: String,
  @JsonProperty("message") val message: String?)
