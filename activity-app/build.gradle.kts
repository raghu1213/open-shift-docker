import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

group = "com.xchange.gambool"

plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("jvm")
  kotlin("plugin.spring")
}

springBoot {
  mainClassName = "com.xchange.gambool.activity.ActivityApplicationKt"
}

release {
  revertOnFail = true
  preTagCommitMessage = "[Gradle Release Plugin] - pre tag commit: [skip ci]"
  tagCommitMessage = "[Gradle Release Plugin] - creating tag: [skip ci]"
  newVersionCommitMessage = "[Gradle Release Plugin] - new version commit: [skip ci]"
}

sourceSets {
  create("test-integration") {
    withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
      kotlin.srcDir(file("src/test-integration/kotlin"))
      resources.srcDir(file("src/test-integration/resources"))
      compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
      runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
    }
  }
}

(tasks.getByName("processResources") as ProcessResources).apply {
  filesMatching("application.properties") {
    expand(project.properties)
  }
}

tasks.register<Test>("integration-test") {
  description = "Runs the integration tests."
  group = "verification"
  testClassesDirs = sourceSets["test-integration"].output.classesDirs
  classpath = sourceSets["test-integration"].runtimeClasspath
  mustRunAfter(tasks["test"])
}

tasks.named("check") {
  dependsOn("integration-test")
}
tasks.bootRun {
  val activeProfile = System.getProperty("spring.profiles.active")
  if (activeProfile.isNullOrEmpty()){
    systemProperty("spring.profiles.active", "test")
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



