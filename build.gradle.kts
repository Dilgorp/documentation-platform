import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion by extra { "1.8.22" }
val platformVersion by extra { "0.0.1-SNAPSHOT" }
val jacksonAnnotationsVersion by extra { "2.15.1" }

plugins {
    idea

    val kotlinVersion = "1.8.22"

    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
}

subprojects {
    apply {
        plugin("java")
        plugin("idea")
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
    }
    group = "ru.dilgorp.documentation.platform"
    configurations {
        all { exclude(module = "slf4j-log4j12") }
        all { exclude(module = "slf4j-simple") }
        all { exclude(module = "slf4j-log4j") }
        all { exclude(module = "tomcat-jdbc") }
        all { exclude(module = "tomcat-embed-el") }
        all { exclude(module = "spring-boot-starter-tomcat") }
        all { exclude(group = "org.apache.logging.log4j") }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.springframework.boot:spring-boot-starter")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    }

    configurations {
        "implementation" {
            resolutionStrategy.failOnVersionConflict()
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
