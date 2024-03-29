import org.springframework.boot.gradle.tasks.bundling.BootJar

val jacksonAnnotationsVersion: String by rootProject.extra

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

plugins {
    idea
    kotlin("jvm") version "1.8.22"
}


val bootJar: BootJar by tasks

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonAnnotationsVersion")
}

bootJar.enabled = false