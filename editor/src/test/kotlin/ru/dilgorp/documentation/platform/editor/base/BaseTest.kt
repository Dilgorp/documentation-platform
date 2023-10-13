package ru.dilgorp.documentation.platform.editor.base

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import ru.dilgorp.documentation.platform.editor.configurations.PostgresContainerInitializer

@SpringBootTest
@ActiveProfiles
@ContextConfiguration(
    initializers = [
        PostgresContainerInitializer::class,
    ]
)
abstract class BaseTest