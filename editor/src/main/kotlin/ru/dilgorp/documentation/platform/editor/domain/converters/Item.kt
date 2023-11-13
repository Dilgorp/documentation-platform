package ru.dilgorp.documentation.platform.editor.domain.converters

import ru.dilgorp.documentation.platform.domain.models.*
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemCategoryEntity
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemEntity
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemPropertyEntity

fun ItemEntity.toModel(): Item = Item(
    id = id,
    title = title,
    description = description,
    categories = emptyList(),
    properties = emptyList(),
)

fun ItemEntity.toModelList(): ItemList = ItemList(
    id = id,
    title = title,
    description = description,
)

fun ItemEntity.toModel(categories: List<ItemCategory>, properties: List<ItemProperty>): Item = Item(
    id = id,
    title = title,
    description = description,
    categories = categories.filter { it.itemId == id },
    properties = properties.filter { it.itemId == id },
)

fun ItemCategoryEntity.toModel(category: Category, parentCategory: Category?): ItemCategory = ItemCategory(
    id = id,
    itemId = itemId,
    category = if (category.id == categoryId) category else throw IllegalArgumentException("Category id and categoryId are different"),
    parentCategory = if (parentCategory?.id == parentCategoryId) parentCategory else throw IllegalArgumentException("Parent category id and parentCategoryId are different"),
    value = categoryValue,
)

fun ItemPropertyEntity.toModel(property: Property): ItemProperty = ItemProperty(
    id = id,
    itemId = itemId,
    property = if (property.id == propertyId) property else throw IllegalArgumentException("Property id and propertyId are different"),
    value = propertyValue,
)

fun Item.toEntity(): ItemEntity = ItemEntity(
    id = id,
    title = title,
    description = description,
)

fun ItemCategory.toEntity(): ItemCategoryEntity = ItemCategoryEntity(
    id = id,
    itemId = itemId,
    parentCategoryId = parentCategory?.id,
    categoryId = requireNotNull(category.id),
    categoryValue = value,
)

fun ItemProperty.toEntity(): ItemPropertyEntity = ItemPropertyEntity(
    id = id,
    itemId = itemId,
    propertyId = requireNotNull(property.id),
    propertyValue = value,
)

fun PatchItemProperty.toEntity(): ItemPropertyEntity = ItemPropertyEntity(
    id = id,
    itemId = itemId,
    propertyId = propertyId,
    propertyValue = value,
)

fun PatchItemCategory.toEntity(): ItemCategoryEntity = ItemCategoryEntity(
    id = id,
    itemId = itemId,
    categoryId = categoryId,
    parentCategoryId = parentCategoryId,
    categoryValue = value,
)