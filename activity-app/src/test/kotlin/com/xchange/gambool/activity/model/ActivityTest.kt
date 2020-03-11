package com.xchange.gambool.activity.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class ActivityTest {

    @Test
    fun `when values are same in two objects then they are treated equal`() {
      val activity1 = Activity(1,"test", intArrayOf(1),"test", true)
      val activity2 = Activity(1,"test", intArrayOf(1),"test", true)
        .withModifiedDate(activity1.modifiedDate)
        .withCreatedDate(activity1.createdDate)
      assertEquals(activity1,activity2)
      assertEquals(activity1.hashCode(),activity2.hashCode())
    }

    @Test
    fun `when values are not same in two objects then they are not equal`() {
      val activity1 = Activity(1,"test", intArrayOf(1),"test", false)
      val activity2 = Activity(1,"test", intArrayOf(1),"test", true)
      assertNotEquals(activity1,activity2)
      assertNotEquals(activity1.hashCode(),activity2.hashCode())
    }

}
