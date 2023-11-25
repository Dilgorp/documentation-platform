package ru.dilgorp.documentation.platform.schema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SchemaApplication

fun main(args: Array<String>) {
    runApplication<SchemaApplication>(*args)
}
