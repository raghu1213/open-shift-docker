import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent
group = "com.xchange.gambool"
buildscript {
  repositories {
    mavenCentral()
  }
}

plugins {
  java
  id("org.springframework.boot") version "2.2.5.RELEASE" apply false
  id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
  kotlin("jvm") version "1.3.70" apply false
  kotlin("plugin.spring") version "1.3.61" apply false
  id("net.researchgate.release") version "2.6.0"
}
//release {
//  failOnCommitNeeded = true
//  failOnPublishNeeded = true
//  failOnSnapshotDependencies = true
//  failOnUnversionedFiles = true
//  failOnUpdateNeeded = true
//  revertOnFail = true
//  preCommitText = ""
//  preTagCommitMessage = "[Gradle Release Plugin] - pre tag commit: "
//  tagCommitMessage = "[Gradle Release Plugin] - creating tag: "
//  newVersionCommitMessage = "[Gradle Release Plugin] - new version commit: "
//  tagTemplate = "$version"
//  //  versionPropertyFile = "gradle.properties"
////  //buildTasks = ["build"]
////  ignoredSnapshotDependencies = []
////  pushReleaseVersionBranch = false
////  git {
////    requireBranch = "master"
////    pushToRemote = "origin"
////    pushToBranchPrefix = ""
////    commitVersionFileOnly = false
////    signTag = false
////  }
//
//}
subprojects {
  repositories {
    jcenter()
  }

  apply {
    plugin("io.spring.dependency-management")
    plugin("java")
  }

  tasks.withType<Test> {
    useJUnitPlatform()
    testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "13"
    }
  }

}

gradle.buildFinished {
  println("deleting " + project.buildDir)
  project.buildDir.deleteRecursively()
}














