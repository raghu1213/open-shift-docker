package com.xchange.gambool.activity

import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.hamcrest.CoreMatchers.`is` as Is

@ExtendWith(SpringExtension::class)
class ActivityApplicationTest {
  @Test
  fun thisIsAUnitTest() {
    println("This is from unit test")
    assertThat("unit test", Is(not(nullValue())))
  }

}
