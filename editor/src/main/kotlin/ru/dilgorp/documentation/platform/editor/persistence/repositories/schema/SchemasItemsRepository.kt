package ru.dilgorp.documentation.platform.editor.persistence.repositories.schema

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaItemEntity

interface SchemasItemsRepository : CrudRepository<SchemaItemEntity, Long> {
    fun findBySchemaIdAndItemId(schemaId: Long, itemId: Long): SchemaItemEntity?
    fun findAllBySchemaId(schemaId: Long): List<SchemaItemEntity>
}