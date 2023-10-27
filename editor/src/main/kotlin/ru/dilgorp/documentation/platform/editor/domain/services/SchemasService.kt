package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.dilgorp.documentation.platform.domain.models.PatchSchemaItem
import ru.dilgorp.documentation.platform.domain.models.Schema
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.repositories.schema.SchemasItemsRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.schema.SchemasRepository

@Service
class SchemasService(
    private val schemasRepository: SchemasRepository,
    private val schemasItemsRepository: SchemasItemsRepository,
) {

    fun save(schema: Schema): Schema =
        schemasRepository.save(schema.toEntity()).toModel()

    fun findById(schemaId: Long): Schema =
        schemasRepository.findById(schemaId).get().toModel()

    fun findAll(): List<Schema> =
        schemasRepository.findAll().map { it.toModel() }

    @Transactional
    fun createOrUpdateItem(patchSchemaItem: PatchSchemaItem) {
        val schemaItemId = patchSchemaItem.id
        val schemaItemEntity = if (schemaItemId != null) {
            val entity = schemasItemsRepository.findById(schemaItemId).get()
            entity.copy(itemId = patchSchemaItem.itemId)
        } else {
            patchSchemaItem.toEntity()
        }

        schemasItemsRepository.save(schemaItemEntity)
    }
}