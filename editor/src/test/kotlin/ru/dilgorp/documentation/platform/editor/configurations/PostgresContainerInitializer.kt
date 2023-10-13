package ru.dilgorp.documentation.platform.editor.configurations

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.PostgreSQLContainerProvider

class PostgresContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        val postgresSQLContainer: JdbcDatabaseContainer<*> = PostgreSQLContainerProvider()
            .newInstance()
            .withDatabaseName("schema-editor-test-db")
            .withUsername("sa")
            .withPassword("sa")
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        postgresSQLContainer.portBindings = listOf("35432:5432")
        postgresSQLContainer.start()

        TestPropertyValues.of(
            "spring.datasource.url=" + postgresSQLContainer.jdbcUrl,
            "spring.datasource.username=" + postgresSQLContainer.username,
            "spring.datasource.password=" + postgresSQLContainer.password,
        ).applyTo(applicationContext.environment)
    }
}