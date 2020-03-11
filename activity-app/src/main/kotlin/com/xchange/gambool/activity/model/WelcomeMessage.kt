package com.xchange.gambool.activity.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
@JsonPropertyOrder("module","message")
data class WelcomeMessage(
        @JsonIgnore  val code: Int,
        @Field val module: String,
        @Field var message: String)



