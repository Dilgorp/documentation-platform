package ru.dilgorp.documentation.platform.editor.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.test.web.servlet.ResultActions
import kotlin.reflect.KClass

object JacksonObjectMapper : ObjectMapper() {
    init {
        registerModule(KotlinModule.Builder().build())
        registerModule(JavaTimeModule())
    }
}

fun <T : Any> ResultActions.andReturn(clazz: KClass<T>): T =
    JacksonObjectMapper.readValue(this.andReturn().response.contentAsByteArray, clazz.java)

fun <T : Any> ResultActions.andReturn(typeReference: TypeReference<T>): T =
    JacksonObjectMapper.readValue(this.andReturn().response.contentAsByteArray, typeReference)