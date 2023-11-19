package ru.dilgorp.documentation.platform.editor.domain.services

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.editor.base.BaseServiceTest
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.data.propertyEntity
import java.util.*

internal class PropertiesServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var propertiesService: PropertiesService

    @Test
    fun `save - happy path`() {
        val model = property()
        val entity = model.toEntity()

        whenever(propertiesRepository.save(entity)).thenReturn(entity)
        val result = propertiesService.save(model)

        assertEquals(model, result)
        verify(propertiesRepository).save(entity)
    }

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = property(id = id)

        whenever(propertiesRepository.findById(id))
            .thenReturn(Optional.of(model.toEntity()))

        val result = propertiesService.findById(id)

        assertEquals(model, result)
        verify(propertiesRepository).findById(id)
    }

    @Test
    fun `findById - entity not found`() {
        val id = randomId()

        whenever(propertiesRepository.findById(id))
            .thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> {
            propertiesService.findById(id)
        }

        verify(propertiesRepository).findById(id)
    }

    @Test
    fun `findAllByIds - happy path`() {
        val notFoundId = randomId()

        val ids = listOf(
            randomId(),
            randomId(),
            randomId(),
        )

        val entities = ids.map { propertyEntity(id = it) }
        val map = entities.associate {
            it.id!! to it.toModel()
        }

        whenever(propertiesRepository.findAllById(ids + notFoundId))
            .thenReturn(entities)

        val result = propertiesService.findAllByIds(ids + notFoundId)

        assertEquals(map, result)
        verify(propertiesRepository).findAllById(ids + notFoundId)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            property(),
            property(),
            property(),
        )

        whenever(propertiesRepository.findAll())
            .thenReturn(models.map { it.toEntity() })

        val result = propertiesService.findAll()

        assertEquals(models, result)
        verify(propertiesRepository).findAll()
    }

    @Test
    fun `findByTitle - happy path`() {
        val title = randomUuid()
        val propertyEntity = propertyEntity()

        whenever(propertiesRepository.findByTitle(title))
            .thenReturn(propertyEntity)

        val result = propertiesService.findByTitle(title)
        assertEquals(propertyEntity.toModel(), result)
        verify(propertiesRepository).findByTitle(title)
    }

}