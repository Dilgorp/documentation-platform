package ru.dilgorp.documentation.platform.editor.domain.services

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseServiceTest
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.data.categoryEntity
import java.util.*

internal class CategoriesServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var categoriesService: CategoriesService

    @Test
    fun `save - happy path`() {
        val model = category()
        val entity = model.toEntity()

        whenever(categoriesRepository.save(entity)).thenReturn(entity)
        val result = categoriesService.save(model)

        assertEquals(model, result)
        verify(categoriesRepository).save(entity)
    }

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = category(id = id)

        whenever(categoriesRepository.findById(id))
            .thenReturn(Optional.of(model.toEntity()))

        val result = categoriesService.findById(id)

        assertEquals(model, result)
        verify(categoriesRepository).findById(id)
    }

    @Test
    fun `findById - entity not found`() {
        val id = randomId()

        whenever(categoriesRepository.findById(id))
            .thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> {
            categoriesService.findById(id)
        }

        verify(categoriesRepository).findById(id)
    }

    @Test
    fun `findAllByIds - happy path`() {
        val notFoundId = randomId()

        val ids = listOf(
            randomId(),
            randomId(),
            randomId(),
        )

        val entities = ids.map { categoryEntity(id = it) }
        val map = entities.associate {
            it.id!! to it.toModel()
        }

        whenever(categoriesRepository.findAllById(ids + notFoundId))
            .thenReturn(entities)

        val result = categoriesService.findAllByIds(ids + notFoundId)

        assertEquals(map, result)
        verify(categoriesRepository).findAllById(ids + notFoundId)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            category(),
            category(),
            category(),
        )

        whenever(categoriesRepository.findAll())
            .thenReturn(models.map { it.toEntity() })

        val result = categoriesService.findAll()

        assertEquals(models, result)
        verify(categoriesRepository).findAll()
    }

}