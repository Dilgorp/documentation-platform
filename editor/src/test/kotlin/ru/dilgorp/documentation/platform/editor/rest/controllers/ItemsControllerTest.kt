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
import ru.dilgorp.documentation.platform.domain.dto.ItemDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.domain.test.data.item.item
import ru.dilgorp.documentation.platform.domain.test.data.item.patchCategoryDto
import ru.dilgorp.documentation.platform.domain.test.data.item.patchPropertyDto
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseControllerTest
import ru.dilgorp.documentation.platform.editor.utils.andReturn

/**
 * Test controller for [ItemsController]
 */
class ItemsControllerTest : BaseControllerTest() {

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = item(id = id)

        whenever(itemsService.findById(id)).thenReturn(model)

        val result = mvc.perform(
            get("/items/$id")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(ItemDto::class)

        assertEquals(model.toDto(), result)
        verify(itemsService).findById(id)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            item(),
            item(),
            item(),
        )

        whenever(itemsService.findAll()).thenReturn(models)

        val result = mvc.perform(
            get("/items")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<List<ItemDto>>() {})

        assertEquals(models.map { it.toDto() }, result)
        verify(itemsService).findAll()
    }

    @Test
    fun `findAllById - happy path`() {
        val modelsMap = listOf(
            item(),
            item(),
            item(),
        ).associateBy { it.id!! }

        val ids = modelsMap.keys.toList()

        val dtoMap = modelsMap.map { it.key to it.value.toDto() }.toMap()

        whenever(itemsService.findAllByIds(ids)).thenReturn(modelsMap)

        val result = mvc.perform(
            get("/items/map?${ids.joinToString(prefix = "ids=") { it.toString() }}")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<Map<Long, ItemDto>>() {})

        assertEquals(dtoMap, result)
        verify(itemsService).findAllByIds(ids)
    }

    @Test
    fun `create - happy path`() {
        val item = item()
        val dto = item.toDto()

        whenever(itemsService.save(item)).thenReturn(item)

        val result = mvc.perform(
            post("/items")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(ItemDto::class)

        assertEquals(dto, result)
        verify(itemsService).save(item)
    }

    @Test
    fun `createProperty - happy path`() {
        val itemId = randomId()
        val dto = patchPropertyDto()

        mvc.perform(
            post("/items/$itemId/properties")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(itemsService).createOrUpdateProperty(dto.toModel(itemId))
    }

    @Test
    fun `updateProperty - happy path`() {
        val itemId = randomId()
        val itemPropertyId = randomId()
        val dto = patchPropertyDto()

        mvc.perform(
            patch("/items/$itemId/properties/$itemPropertyId")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(itemsService).createOrUpdateProperty(dto.toModel(itemPropertyId, itemId))
    }

    @Test
    fun `createCategory - happy path`() {
        val itemId = randomId()
        val dto = patchCategoryDto()

        mvc.perform(
            post("/items/$itemId/categories")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(itemsService).createOrUpdateCategory(dto.toModel(itemId))
    }

    @Test
    fun `updateCategory - happy path`() {
        val itemId = randomId()
        val itemCategoryId = randomId()
        val dto = patchCategoryDto()

        mvc.perform(
            patch("/items/$itemId/categories/$itemCategoryId")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(itemsService).createOrUpdateCategory(dto.toModel(itemCategoryId, itemId))
    }
}