package ru.dilgorp.documentation.platform.editor.domain.services

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import ru.dilgorp.documentation.platform.domain.test.data.schema.patchSchemaItem
import ru.dilgorp.documentation.platform.domain.test.data.schema.schema
import ru.dilgorp.documentation.platform.domain.test.data.schema.schemaItem
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseServiceTest
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import java.util.*

internal class SchemasServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var schemasService: SchemasService

    @MockBean
    private lateinit var itemsService: ItemsService

    @Test
    fun `save - happy path`() {
        val model = schema(items = emptyList())
        val entity = model.toEntity()

        whenever(schemasRepository.save(entity)).thenReturn(entity)
        whenever(schemasRepository.findById(entity.id!!)).thenReturn(Optional.of(entity))
        whenever(schemasItemsRepository.findAllBySchemaId(entity.id!!)).thenReturn(emptyList())
        whenever(itemsService.findAllByIds(emptyList())).thenReturn(emptyMap())

        val result = schemasService.save(model)

        assertEquals(model, result)
        verify(schemasRepository).save(entity)
        verify(schemasRepository).findById(entity.id!!)
        verify(schemasItemsRepository).findAllBySchemaId(entity.id!!)
        verify(itemsService).findAllByIds(emptyList())
    }

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = schema(
            id = id,
            items = listOf(
                schemaItem(schemaId = id),
                schemaItem(schemaId = id),
            ),
        )
        val schemaItemsEntities = model.items.map { it.toEntity() }

        whenever(schemasRepository.findById(id))
            .thenReturn(Optional.of(model.toEntity()))
        whenever(schemasItemsRepository.findAllBySchemaId(id))
            .thenReturn(schemaItemsEntities)
        whenever(itemsService.findAllByIds(schemaItemsEntities.map { it.itemId }))
            .thenReturn(model.items.associate { it.item.id!! to it.item })

        val result = schemasService.findById(id)

        assertEquals(model, result)
        verify(schemasRepository).findById(id)
        verify(schemasItemsRepository).findAllBySchemaId(id)
        verify(itemsService).findAllByIds(schemaItemsEntities.map { it.itemId })
    }

    @Test
    fun `findById - entity not found`() {
        val id = randomId()

        whenever(schemasRepository.findById(id))
            .thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> {
            schemasService.findById(id)
        }

        verify(schemasRepository).findById(id)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            schema(items = emptyList()),
            schema(items = emptyList()),
            schema(items = emptyList()),
        )

        whenever(schemasRepository.findAll())
            .thenReturn(models.map { it.toEntity() })

        val result = schemasService.findAll()

        assertEquals(models, result)
        verify(schemasRepository).findAll()
    }

    @Test
    fun `createOrUpdateItem - happy path`() {
        val patchSchemaItem = patchSchemaItem(itemId = 1)

        whenever(schemasItemsRepository.findById(patchSchemaItem.id!!))
            .thenReturn(Optional.of(patchSchemaItem.toEntity().copy(itemId = 2)))

        schemasService.createOrUpdateItem(patchSchemaItem)

        verify(schemasItemsRepository).findById(patchSchemaItem.id!!)
        verify(schemasItemsRepository).save(patchSchemaItem.toEntity())
    }

    @Test
    fun `createOrUpdateItem - create`() {
        val patchSchemaItem = patchSchemaItem(id = null)

        schemasService.createOrUpdateItem(patchSchemaItem)

        verify(schemasItemsRepository, times(0)).findById(any())
        verify(schemasItemsRepository).save(patchSchemaItem.toEntity())
    }
}