package com.xchange.gambool.activity.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.BsonTimestamp
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class Activity(
  @Field val activityId: Int,
  @Field val name: String,
  @Field val tagIds: IntArray,
  @Field var description: String,
  @Field var isActive: Boolean
) {
  @JsonIgnore
  @Id
  @Field("_id")
  lateinit var id: ObjectId

  @Field
  var createdDate: BsonTimestamp = BsonTimestamp(System.nanoTime())

  @Field
  var modifiedDate: BsonTimestamp = BsonTimestamp(System.nanoTime())

  fun withId(id: ObjectId): Activity {
    this.id = id
    return this
  }

  fun withModifiedDate(modifiedDate: BsonTimestamp): Activity {
    this.modifiedDate = modifiedDate
    return this
  }

  fun withCreatedDate(createdDate: BsonTimestamp): Activity {
    this.createdDate = createdDate
    return this
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Activity

    if (activityId != other.activityId) return false
    if (name != other.name) return false
    if (!tagIds.contentEquals(other.tagIds)) return false
    if (description != other.description) return false
    if (isActive != other.isActive) return false
    if (createdDate != other.createdDate) return false
    if (modifiedDate != other.modifiedDate) return false

    return true
  }

  override fun hashCode(): Int {
    var result = activityId
    result = 31 * result + name.hashCode()
    result = 31 * result + tagIds.contentHashCode()
    result = 31 * result + description.hashCode()
    result = 31 * result + isActive.hashCode()
    result = 31 * result + createdDate.hashCode()
    result = 31 * result + modifiedDate.hashCode()
    return result
  }

  override fun toString(): String {
    return "Activity(activityId=$activityId," +
      " name='$name'," +
      " tagIds=${tagIds.contentToString()}," +
      " description='$description'," +
      " isActive=$isActive," +
      " createdDate=$createdDate," +
      " modifiedDate=$modifiedDate)"
  }


}
