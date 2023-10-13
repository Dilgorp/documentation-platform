package ru.dilgorp.documentation.platform.editor.persistence.repositories.schema

import org.junit.jupiter.api.Assertions.assertEquals
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
}