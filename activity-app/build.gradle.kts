import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

group = "com.xchange.gambool"
version = "0.0.1-SNAPSHOT"

plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("jvm")
  kotlin("plugin.spring")
}
sourceSets {
  create("test-integration") {
    withConvention(KotlinSourceSet::class) {
      kotlin.srcDir(file("src/test-integration/kotlin"))
      resources.srcDir(file("src/test-integration/resources"))
      compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
      runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
    }
  }
}



val jar by tasks.getting(Jar::class) {
  manifest {
    attributes["Main-Class"] = "com.xchange.gambool.activity.ActivityApplication"
  }
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("io.springfox:springfox-swagger2:2.9.2")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}

tasks.register<Test>("test-integration") {
  description = "Runs the integration tests."
  group = "verification"
  testClassesDirs = sourceSets["test-integration"].output.classesDirs
  classpath = sourceSets["test-integration"].runtimeClasspath
  mustRunAfter(tasks["test"])
}

tasks.named("check") {
  dependsOn("test-integration")
}



