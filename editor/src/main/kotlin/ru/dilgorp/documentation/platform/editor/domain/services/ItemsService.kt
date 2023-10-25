package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.PatchItemProperty
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsPropertiesRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsRepository

@Service
class ItemsService(
    private val itemsRepository: ItemsRepository,
    private val itemsPropertiesRepository: ItemsPropertiesRepository,
) {

    fun save(item: Item): Item =
        itemsRepository.save(item.toEntity()).toModel()

    fun findById(itId: Long): Item =
        itemsRepository.findById(itId).get().toModel()

    fun findAllByIds(itemsIds: List<Long>): Map<Long, Item> =
        itemsRepository.findAllById(itemsIds)
            .associate { requireNotNull(it.id) to it.toModel() }

    fun findAll(): List<Item> =
        itemsRepository.findAll().map { it.toModel() }

    @Transactional
    fun createOrUpdate(patchItemProperty: PatchItemProperty) {
        val itemPropertyId = patchItemProperty.id
        val itemPropertyEntity = if (itemPropertyId != null) {
            itemsPropertiesRepository.findById(itemPropertyId).get()
        } else {
            itemsPropertiesRepository.findByItemIdAndPropertyId(patchItemProperty.itemId, patchItemProperty.propertyId)
        }

        val entity = itemPropertyEntity?.copy(propertyValue = patchItemProperty.value)
            ?: patchItemProperty.toEntity()

        itemsPropertiesRepository.save(entity)
    }
}