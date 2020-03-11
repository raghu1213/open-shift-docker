import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    mavenCentral()
  }
}

plugins {
  id("org.springframework.boot") version "2.2.5.RELEASE" apply false
  id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
  kotlin("jvm") version "1.3.70" apply false
  kotlin("plugin.spring") version "1.3.61" apply false
}

group = "com.xchange.gambool"
version = "0.0.1-SNAPSHOT"


subprojects {
  repositories {
    mavenCentral()
  }

//  apply {
//    plugin("io.spring.dependency-management")
//  }



  tasks.withType<Test> {
    useJUnitPlatform()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "11"
    }
  }


}

gradle.buildFinished {
  println("deleting " + project.buildDir)
  project.buildDir.deleteRecursively()
}















