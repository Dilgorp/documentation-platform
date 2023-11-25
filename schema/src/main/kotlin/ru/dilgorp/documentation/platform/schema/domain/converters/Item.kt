package ru.dilgorp.documentation.platform.schema.domain.converters

import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.ItemCategory
import ru.dilgorp.documentation.platform.domain.models.ItemProperty
import ru.dilgorp.documentation.platform.schema.persistence.entities.item.ItemCategoryEntity
import ru.dilgorp.documentation.platform.schema.persistence.entities.item.ItemEntity
import ru.dilgorp.documentation.platform.schema.persistence.entities.item.ItemPropertyEntity

fun ItemEntity.toModel(): Item = Item(
    id = id,
    title = title,
    description = description,
    categories = categories.map { it.toModel() },
    properties = properties.map { it.toModel() }
)

fun ItemCategoryEntity.toModel(): ItemCategory = ItemCategory(
    id = id,
    itemId = requireNotNull(itemId),
    category = category.toModel(),
    parentCategory = parentCategory?.toModel(),
    value = categoryValue,
)

fun ItemPropertyEntity.toModel(): ItemProperty = ItemProperty(
    id = id,
    itemId = requireNotNull(itemId),
    property = property.toModel(),
    value = propertyValue,
)