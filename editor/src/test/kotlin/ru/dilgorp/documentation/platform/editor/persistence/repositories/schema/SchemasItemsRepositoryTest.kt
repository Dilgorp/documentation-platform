package ru.dilgorp.documentation.platform.editor.persistence.repositories.schema

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.schema.schemaEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.schema.schemaItemEntity
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsRepository

class SchemasItemsRepositoryTest : BaseRepositoryTest() {

    @Autowired
    private lateinit var schemasItemsRepository: SchemasItemsRepository

    @Autowired
    private lateinit var schemasRepository: SchemasRepository

    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @Test
    fun `save - findBy - happy path`() {
        val schemaEntity = schemasRepository.save(schemaEntity(id = null))
        val itemEntity = itemsRepository.save(itemEntity(id = null))

        val entity = schemaItemEntity(
            id = null,
            schemaId = schemaEntity.id!!,
            itemId = itemEntity.id!!,
        )

        val savedEntity = schemasItemsRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundedEntity = schemasItemsRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundedEntity)
    }

    @Test
    fun `findBySchemaIdAndItemId - happy path`() {
        val schemaEntity = schemasRepository.save(schemaEntity(id = null))
        val itemEntity = itemsRepository.save(itemEntity(id = null))

        val entity = schemasItemsRepository.save(
            schemaItemEntity(
                id = null,
                schemaId = schemaEntity.id!!,
                itemId = itemEntity.id!!,
            ),
        )

        val result = schemasItemsRepository.findBySchemaIdAndItemId(schemaEntity.id!!, itemEntity.id!!)
        assertEquals(entity, result)
    }

    @Test
    fun `findBySchemaIdAndItemId - not found`() {
        val schemaEntity = schemasRepository.save(schemaEntity(id = null))
        val itemEntity = itemsRepository.save(itemEntity(id = null))

        val result = schemasItemsRepository.findBySchemaIdAndItemId(schemaEntity.id!!, itemEntity.id!!)
        assertNull(result)
    }

    @Test
    fun `findAllBySchemaId - happy path`() {
        val schemaEntity = schemasRepository.save(schemaEntity(id = null))
        val itemEntity = itemsRepository.save(itemEntity(id = null))

        val schemaEntity2 = schemasRepository.save(schemaEntity(id = null))
        val itemEntity2 = itemsRepository.save(itemEntity(id = null))

        val entities = schemasItemsRepository.saveAll(
            listOf(
                schemaItemEntity(
                    id = null,
                    schemaId = schemaEntity.id!!,
                    itemId = itemEntity.id!!,
                ),
                schemaItemEntity(
                    id = null,
                    schemaId = schemaEntity.id!!,
                    itemId = itemEntity.id!!,
                ),
                schemaItemEntity(
                    id = null,
                    schemaId = schemaEntity.id!!,
                    itemId = itemEntity.id!!,
                ),
                schemaItemEntity(
                    id = null,
                    schemaId = schemaEntity2.id!!,
                    itemId = itemEntity2.id!!,
                ),
            ),
        )

        val result = schemasItemsRepository.findAllBySchemaId(schemaEntity.id!!)

        assertEquals(
            entities.filter { it.schemaId == schemaEntity.id!! }.sortedBy { it.id },
            result.sortedBy { it.id }
        )
    }
}