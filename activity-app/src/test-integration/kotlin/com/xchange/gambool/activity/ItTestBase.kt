package com.xchange.gambool.activity

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@SpringBootTest(classes = [ActivityApplication::class],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = ["test"])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class ItTestBase {

}
