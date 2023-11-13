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
    private val itemsService: ItemsService,
) {

    fun save(schema: Schema): Schema {
        val entity = schemasRepository.save(schema.toEntity())
        return findById(requireNotNull(entity.id))
    }

    fun findById(schemaId: Long): Schema {
        val schemaEntity = schemasRepository.findById(schemaId).get()
        val schemaItemsEntities = schemasItemsRepository.findAllBySchemaId(schemaId)
        val items = itemsService.findAllByIds(schemaItemsEntities.map { it.itemId })
        return schemaEntity.toModel(
            schemaItemsEntities.map { schemaItemEntity ->
                val item = requireNotNull(items[schemaItemEntity.itemId])
                schemaItemEntity.toModel(item)
            }
        )
    }

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