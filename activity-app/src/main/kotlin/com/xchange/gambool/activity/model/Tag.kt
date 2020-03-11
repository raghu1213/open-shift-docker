package com.xchange.gambool.activity.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.BsonTimestamp
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Tag(
  @Field val tagId: Int,
  @Field val name: String,
  @Field var isActive: Boolean
) {

  @Field("_id")
  @JsonIgnore
  @Id
  lateinit var id: ObjectId

  @Field
  var createdDate: BsonTimestamp = BsonTimestamp(System.nanoTime())

  @Field
  var modifiedDate: BsonTimestamp = BsonTimestamp(System.nanoTime())

  fun withId(id: ObjectId): Tag {
    this.id = id
    return this
  }

  fun withModifiedDate(modifiedDate: BsonTimestamp): Tag {
    this.modifiedDate = modifiedDate
    return this
  }

  fun withCreatedDate(createdDate: BsonTimestamp): Tag {
    this.createdDate = createdDate
    return this
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Tag

    if (tagId != other.tagId) return false
    if (name != other.name) return false
    if (isActive != other.isActive) return false
    if (createdDate != other.createdDate) return false
    if (modifiedDate != other.modifiedDate) return false

    return true
  }

  override fun hashCode(): Int {
    var result = tagId
    result = 31 * result + name.hashCode()
    result = 31 * result + isActive.hashCode()
    result = 31 * result + createdDate.hashCode()
    result = 31 * result + modifiedDate.hashCode()
    return result
  }

  override fun toString(): String {
    return "Tag(tagId=$tagId, name='$name', isActive=$isActive, createdDate=$createdDate, modifiedDate=$modifiedDate)"
  }


}
