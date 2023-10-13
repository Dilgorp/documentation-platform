package ru.dilgorp.documentation.platform.editor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@SpringBootApplication
@EnableJdbcRepositories
class EditorApplication

fun main(args: Array<String>) {
    runApplication<EditorApplication>(*args)
}
