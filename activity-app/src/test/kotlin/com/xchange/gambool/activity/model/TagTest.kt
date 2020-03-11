package com.xchange.gambool.activity.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class TagTest {

  @Test
  fun `when values are same for two objects then they are treated equal`() {
    val tag1 = Tag(1, "test", true)
    val tag2 = Tag(1, "test", true)
      .withModifiedDate(tag1.modifiedDate)
      .withCreatedDate(tag1.createdDate)

    assertEquals(tag1.hashCode(), tag2.hashCode())
    assertEquals(tag1, tag2)
  }

  @Test
  fun `when values are different then object are not equal`() {
    val tag1 = Tag(1, "test", false)
    val tag2 = Tag(1, "test", true)
    assertNotEquals(tag1.hashCode(), tag2.hashCode())
    assertNotEquals(tag1, tag2)
  }
}
