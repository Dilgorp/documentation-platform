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
import ru.dilgorp.documentation.platform.domain.dto.CategoryDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.domain.test.data.patchCategoryDto
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseControllerTest
import ru.dilgorp.documentation.platform.editor.utils.andReturn

/**
 * Test controller for [CategoriesController]
 */
class CategoriesControllerTest : BaseControllerTest() {

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = category(id = id)

        whenever(categoriesService.findById(id)).thenReturn(model)

        val result = mvc.perform(
            get("/categories/$id")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(CategoryDto::class)

        assertEquals(model.toDto(), result)
        verify(categoriesService).findById(id)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            category(),
            category(),
            category(),
        )

        whenever(categoriesService.findAll()).thenReturn(models)

        val result = mvc.perform(
            get("/categories")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<List<CategoryDto>>() {})

        assertEquals(models.map { it.toDto() }, result)
        verify(categoriesService).findAll()
    }

    @Test
    fun `findAllById - happy path`() {
        val modelsMap = listOf(
            category(),
            category(),
            category(),
        ).associateBy { it.id!! }

        val ids = modelsMap.keys.toList()

        val dtoMap = modelsMap.map { it.key to it.value.toDto() }.toMap()

        whenever(categoriesService.findAllByIds(ids)).thenReturn(modelsMap)

        val result = mvc.perform(
            get("/categories/map?${ids.joinToString(prefix = "ids=") { it.toString() }}")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(object : TypeReference<Map<Long, CategoryDto>>() {})

        assertEquals(dtoMap, result)
        verify(categoriesService).findAllByIds(ids)
    }

    @Test
    fun `create - happy path`() {
        val category = category()
        val dto = patchCategoryDto(
            title = category.title,
        )

        whenever(categoriesService.save(category.copy(id = null))).thenReturn(category)

        val result = mvc.perform(
            post("/categories")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(CategoryDto::class)

        assertEquals(category.toDto(), result)
        verify(categoriesService).save(category.copy(id = null))
    }

    @Test
    fun `update - happy path`() {
        val categoryId = randomId()

        val category = category(id = categoryId)
        val dto = patchCategoryDto(
            title = category.title,
        )

        whenever(categoriesService.save(category)).thenReturn(category)

        val result = mvc.perform(
            patch("/categories/$categoryId")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn(CategoryDto::class)

        assertEquals(category.toDto(), result)
        verify(categoriesService).save(category)
    }
}