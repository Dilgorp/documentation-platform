package ru.dilgorp.documentation.platform.editor.domain.converters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.dilgorp.documentation.platform.domain.test.data.item.itemList
import ru.dilgorp.documentation.platform.domain.test.data.schema.schema
import ru.dilgorp.documentation.platform.domain.test.data.schema.schemaItem
import ru.dilgorp.documentation.platform.domain.test.utils.diffFromRandom
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.data.schema.schemaEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.schema.schemaItemEntity

internal class SchemaConverterTest {
    @Test
    fun `SchemaEntity - toModel - happy path`() {
        val model = schema(
            id = 1,
            title = "Schema",
            description = "Description",
            items = emptyList()
        )

        val entity = schemaEntity(
            id = 1,
            title = "Schema",
            description = "Description",
        )

        assertEquals(model, entity.toModel())
    }

    @Test
    fun `SchemaEntity - toModel - with schemaItems - happy path`() {
        val schemaId = randomId()
        val schemaItems = listOf(
            schemaItem(schemaId = schemaId),
        )

        val model = schema(
            id = schemaId,
            title = "Schema",
            description = "Description",
            items = schemaItems
        )

        val entity = schemaEntity(
            id = schemaId,
            title = "Schema",
            description = "Description",
        )

        assertEquals(model, entity.toModel(schemaItems))
    }

    @Test
    fun `SchemaEntity - toModel - with schemaItems - mixed schema items`() {
        val schemaId = randomId()
        val schemaItems = listOf(
            schemaItem(schemaId = schemaId),
            schemaItem(schemaId = schemaId.diffFromRandom()),
            schemaItem(schemaId = schemaId.diffFromRandom()),
        )

        val model = schema(
            id = schemaId,
            title = "Schema",
            description = "Description",
            items = schemaItems.filter { it.schemaId == schemaId }
        )

        val entity = schemaEntity(
            id = schemaId,
            title = "Schema",
            description = "Description",
        )

        assertEquals(model, entity.toModel(schemaItems))
    }

    @Test
    fun `Schema - toEntity - happy path`() {
        val model = schema(
            id = 1,
            title = "Schema",
            description = "Description",
            items = emptyList()
        )

        val entity = schemaEntity(
            id = 1,
            title = "Schema",
            description = "Description",
        )

        assertEquals(entity, model.toEntity())
    }

    @Test
    fun `SchemaItemEntity - toModel - happy path`() {
        val itemId = randomId()

        val item = itemList(id = itemId)

        val model = schemaItem(
            id = 1,
            schemaId = 1,
            item = item,
        )
        val entity = schemaItemEntity(
            id = 1,
            schemaId = 1,
            itemId = itemId,
        )

        assertEquals(model, entity.toModel(item))
    }

    @Test
    fun `SchemaItemEntity - toModel - item with invalid id`() {
        val itemId = randomId()

        val item = itemList(id = itemId)

        val entity = schemaItemEntity(
            id = 1,
            schemaId = 1,
            itemId = itemId.diffFromRandom(),
        )

        assertThrows<IllegalArgumentException> {
            entity.toModel(item)
        }
    }

    @Test
    fun `SchemaItem - toEntity - happy path`() {
        val itemId = randomId()

        val item = itemList(id = itemId)

        val model = schemaItem(
            id = 1,
            schemaId = 1,
            item = item,
        )
        val entity = schemaItemEntity(
            id = 1,
            schemaId = 1,
            itemId = itemId,
        )

        assertEquals(entity, model.toEntity())
    }
}