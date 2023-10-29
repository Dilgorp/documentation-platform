package ru.dilgorp.documentation.platform.editor.rest.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.dilgorp.documentation.platform.domain.dto.SchemaDto
import ru.dilgorp.documentation.platform.domain.dto.SchemaListDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.domain.dto.toListDto
import ru.dilgorp.documentation.platform.domain.test.data.schema.patchSchemaDto
import ru.dilgorp.documentation.platform.domain.test.data.schema.patchSchemaItemDto
import ru.dilgorp.documentation.platform.domain.test.data.schema.schema
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseControllerTest
import ru.dilgorp.documentation.platform.editor.utils.andReturn

/**
 * Test controller for [SchemasController]
 */
class SchemasControllerTest : BaseControllerTest() {

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = schema(id = id)

        whenever(schemasService.findById(id)).thenReturn(model)

        val result = mvc.perform(
            get("/schemas/$id")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(SchemaDto::class)

        assertEquals(model.toDto(), result)
        verify(schemasService).findById(id)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            schema(),
            schema(),
            schema(),
        )

        whenever(schemasService.findAll()).thenReturn(models)

        val result = mvc.perform(
            get("/schemas")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<List<SchemaListDto>>() {})

        assertEquals(models.map { it.toListDto() }, result)
        verify(schemasService).findAll()
    }

    @Test
    fun `create - happy path`() {
        val schema = schema(items = emptyList())
        val dto = patchSchemaDto(
            title = schema.title,
            description = schema.description,
        )

        whenever(schemasService.save(schema.copy(id = null))).thenReturn(schema)

        val result = mvc.perform(
            post("/schemas")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(SchemaDto::class)

        assertEquals(schema.toDto(), result)
        verify(schemasService).save(schema.copy(id = null))
    }

    @Test
    fun `update - happy path`() {
        val schemaId = randomId()

        val schema = schema(
            id = schemaId,
            items = emptyList(),
        )
        val dto = patchSchemaDto(
            title = schema.title,
            description = schema.description,
        )

        whenever(schemasService.save(schema)).thenReturn(schema)

        val result = mvc.perform(
            patch("/schemas/$schemaId")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(SchemaDto::class)

        assertEquals(schema.toDto(), result)
        verify(schemasService).save(schema)
    }

    @Test
    fun `createItem - happy path`() {
        val schemaId = randomId()
        val dto = patchSchemaItemDto()

        mvc.perform(
            post("/schemas/$schemaId/items")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(schemasService).createOrUpdateItem(dto.toModel(schemaId))
    }

    @Test
    fun `updateItem - happy path`() {
        val schemaId = randomId()
        val schemaItemId = randomId()
        val dto = patchSchemaItemDto()

        mvc.perform(
            patch("/schemas/$schemaId/items/$schemaItemId")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(schemasService).createOrUpdateItem(dto.toModel(schemaItemId, schemaId))
    }
}