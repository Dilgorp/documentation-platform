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
import ru.dilgorp.documentation.platform.domain.dto.PropertyDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.domain.test.data.patchPropertyDto
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseControllerTest
import ru.dilgorp.documentation.platform.editor.utils.andReturn

/**
 * Test controller for [PropertiesController]
 */
class PropertiesControllerTest : BaseControllerTest() {

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = property(id = id)

        whenever(propertiesService.findById(id)).thenReturn(model)

        val result = mvc.perform(
            get("/properties/$id")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(PropertyDto::class)

        assertEquals(model.toDto(), result)
        verify(propertiesService).findById(id)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            property(),
            property(),
            property(),
        )

        whenever(propertiesService.findAll()).thenReturn(models)

        val result = mvc.perform(
            get("/properties")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<List<PropertyDto>>() {})

        assertEquals(models.map { it.toDto() }, result)
        verify(propertiesService).findAll()
    }

    @Test
    fun `findAllById - happy path`() {
        val modelsMap = listOf(
            property(),
            property(),
            property(),
        ).associateBy { it.id!! }

        val ids = modelsMap.keys.toList()

        val dtoMap = modelsMap.map { it.key to it.value.toDto() }.toMap()

        whenever(propertiesService.findAllByIds(ids)).thenReturn(modelsMap)

        val result = mvc.perform(
            get("/properties/map?${ids.joinToString(prefix = "ids=") { it.toString() }}")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<Map<Long, PropertyDto>>() {})

        assertEquals(dtoMap, result)
        verify(propertiesService).findAllByIds(ids)
    }

    @Test
    fun `create - happy path`() {
        val property = property()
        val dto = patchPropertyDto(
            title = property.title,
        )

        whenever(propertiesService.save(property.copy(id = null))).thenReturn(property)

        val result = mvc.perform(
            post("/properties")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(PropertyDto::class)

        assertEquals(property.toDto(), result)
        verify(propertiesService).save(property.copy(id = null))
    }

    @Test
    fun `update - happy path`() {
        val propertyId = randomId()

        val property = property(id = propertyId)
        val dto = patchPropertyDto(
            title = property.title,
        )

        whenever(propertiesService.save(property)).thenReturn(property)

        val result = mvc.perform(
            patch("/properties/$propertyId")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(PropertyDto::class)

        assertEquals(property.toDto(), result)
        verify(propertiesService).save(property)
    }
}