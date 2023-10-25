package ru.dilgorp.documentation.platform.editor.domain.services

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.data.item.item
import ru.dilgorp.documentation.platform.domain.test.data.item.patchItemProperty
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.editor.base.BaseServiceTest
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemPropertyEntity
import java.util.*

internal class ItemsServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var itemsService: ItemsService

    @Test
    fun `save - happy path`() {
        val model = item()
        val entity = model.toEntity()

        whenever(itemsRepository.save(entity)).thenReturn(entity)
        val result = itemsService.save(model)

        assertEquals(model, result)
        verify(itemsRepository).save(entity)
    }

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = item(id = id)

        whenever(itemsRepository.findById(id))
            .thenReturn(Optional.of(model.toEntity()))

        val result = itemsService.findById(id)

        assertEquals(model, result)
        verify(itemsRepository).findById(id)
    }

    @Test
    fun `findById - entity not found`() {
        val id = randomId()

        whenever(itemsRepository.findById(id))
            .thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> {
            itemsService.findById(id)
        }

        verify(itemsRepository).findById(id)
    }

    @Test
    fun `findAllByIds - happy path`() {
        val notFoundId = randomId()

        val ids = listOf(
            randomId(),
            randomId(),
            randomId(),
        )

        val entities = ids.map { itemEntity(id = it) }
        val map = entities.associate {
            it.id!! to it.toModel()
        }

        whenever(itemsRepository.findAllById(ids + notFoundId))
            .thenReturn(entities)

        val result = itemsService.findAllByIds(ids + notFoundId)

        assertEquals(map, result)
        verify(itemsRepository).findAllById(ids + notFoundId)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            item(),
            item(),
            item(),
        )

        whenever(itemsRepository.findAll())
            .thenReturn(models.map { it.toEntity() })

        val result = itemsService.findAll()

        assertEquals(models, result)
        verify(itemsRepository).findAll()
    }

    @Test
    fun `createOrUpdate - create - happy path`() {
        val itemProperty = patchItemProperty(id = null)

        whenever(itemsPropertiesRepository.findByItemIdAndPropertyId(itemProperty.itemId, itemProperty.propertyId))
            .thenReturn(null)

        itemsService.createOrUpdate(itemProperty)

        verify(itemsPropertiesRepository, times(0)).findById(any())
        verify(itemsPropertiesRepository).findByItemIdAndPropertyId(itemProperty.itemId, itemProperty.propertyId)
        verify(itemsPropertiesRepository).save(itemProperty.toEntity())
    }

    @Test
    fun `createOrUpdate - update - happy path`() {
        val itemProperty = patchItemProperty()

        val itemPropertyEntity = itemPropertyEntity(
            id = itemProperty.id!!,
            itemId = itemProperty.itemId,
            propertyId = itemProperty.propertyId,
        )

        whenever(itemsPropertiesRepository.findById(itemProperty.id!!))
            .thenReturn(Optional.of(itemPropertyEntity))

        itemsService.createOrUpdate(itemProperty)

        verify(itemsPropertiesRepository).findById(itemProperty.id!!)
        verify(itemsPropertiesRepository, times(0)).findByItemIdAndPropertyId(any(), any())
        verify(itemsPropertiesRepository).save(itemPropertyEntity.copy(propertyValue = itemProperty.value))
    }

    @Test
    fun `createOrUpdate - update - by itemId and propertyId`() {
        val propertyValue = randomUuid()

        val itemProperty = patchItemProperty(id = null)
        val itemPropertyEntity = itemPropertyEntity(
            id = randomId(),
            itemId = itemProperty.itemId,
            propertyId = itemProperty.propertyId,
            propertyValue = propertyValue,
        )

        whenever(itemsPropertiesRepository.findByItemIdAndPropertyId(itemProperty.itemId, itemProperty.propertyId))
            .thenReturn(itemPropertyEntity)

        itemsService.createOrUpdate(itemProperty)

        verify(itemsPropertiesRepository, times(0)).findById(any())
        verify(itemsPropertiesRepository).findByItemIdAndPropertyId(itemProperty.itemId, itemProperty.propertyId)
        verify(itemsPropertiesRepository).save(itemPropertyEntity.copy(propertyValue = itemProperty.value))
    }
}