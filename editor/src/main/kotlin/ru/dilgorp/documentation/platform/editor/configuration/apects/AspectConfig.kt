package ru.dilgorp.documentation.platform.editor.configuration.apects

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AspectConfig {

    @Bean
    fun loggingAspect() = LoggingAspect()
}