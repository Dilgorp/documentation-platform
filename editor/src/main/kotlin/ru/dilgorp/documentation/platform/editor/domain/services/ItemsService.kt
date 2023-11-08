package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.PatchItemCategory
import ru.dilgorp.documentation.platform.domain.models.PatchItemProperty
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemCategoryEntity
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemEntity
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemPropertyEntity
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsCategoriesRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsPropertiesRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsRepository

@Service
class ItemsService(
    private val itemsRepository: ItemsRepository,
    private val itemsPropertiesRepository: ItemsPropertiesRepository,
    private val itemsCategoriesRepository: ItemsCategoriesRepository,
    private val categoriesService: CategoriesService,
    private val propertyService: PropertiesService,
) {

    fun save(item: Item): Item {
        val itemId = requireNotNull(itemsRepository.save(item.toEntity()).toModel().id)

        return findById(itemId)
    }

    fun findById(id: Long): Item {
        val itemProperties = itemsPropertiesRepository.findAllByItemId(id)
        val itemCategories = itemsCategoriesRepository.findAllByItemId(id)

        return toModel(itemsRepository.findById(id).get(), itemCategories, itemProperties)
    }

    fun findAllByIds(itemsIds: List<Long>): Map<Long, Item> =
        itemsRepository.findAllById(itemsIds)
            .associate { requireNotNull(it.id) to it.toModel() }

    fun findAll(): List<Item> =
        itemsRepository.findAll().map { it.toModel() }

    @Transactional
    fun createOrUpdateProperty(patchItemProperty: PatchItemProperty) {
        val itemPropertyId = patchItemProperty.id
        val itemPropertyEntity = if (itemPropertyId != null) {
            itemsPropertiesRepository.findById(itemPropertyId).get()
        } else {
            itemsPropertiesRepository.findByItemIdAndPropertyId(patchItemProperty.itemId, patchItemProperty.propertyId)
        }

        val entity = itemPropertyEntity?.copy(
            propertyId = patchItemProperty.propertyId,
            propertyValue = patchItemProperty.value
        ) ?: patchItemProperty.toEntity()

        itemsPropertiesRepository.save(entity)
    }

    @Transactional
    fun createOrUpdateCategory(patchItemCategory: PatchItemCategory) {
        val itemCategoryId = patchItemCategory.id
        val itemCategoryEntity = if (itemCategoryId != null) {
            itemsCategoriesRepository.findById(itemCategoryId).get()
        } else {
            itemsCategoriesRepository.findByItemIdAndCategoryId(patchItemCategory.itemId, patchItemCategory.categoryId)
        }

        val entity = itemCategoryEntity?.copy(
            categoryId = patchItemCategory.categoryId,
            parentCategoryId = patchItemCategory.parentCategoryId,
            categoryValue = patchItemCategory.value,
        ) ?: patchItemCategory.toEntity()

        itemsCategoriesRepository.save(entity)
    }

    private fun toModel(
        entity: ItemEntity,
        itemCategories: List<ItemCategoryEntity>,
        itemProperties: List<ItemPropertyEntity>,
    ): Item {
        return entity.toModel(
            categories = itemCategories.map { itemCategoryEntity ->
                val category = categoriesService.findById(itemCategoryEntity.categoryId)
                val parentCategory = itemCategoryEntity.parentCategoryId?.let {
                    categoriesService.findById(it)
                }
                itemCategoryEntity.toModel(category, parentCategory)
            },
            properties = itemProperties.map { itemPropertyEntity ->
                val property = propertyService.findById(itemPropertyEntity.propertyId)
                itemPropertyEntity.toModel(property)
            }
        )
    }
}