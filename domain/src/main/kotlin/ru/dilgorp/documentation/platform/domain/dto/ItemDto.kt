package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.ItemCategory
import ru.dilgorp.documentation.platform.domain.models.ItemProperty

data class ItemListDto(
    val id: Long,
    val title: String,
)

fun Item.toListDto(): ItemListDto = ItemListDto(
    id = requireNotNull(id),
    title = title,
)

data class ItemDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val categories: List<ItemCategoryDto> = emptyList(),
    val properties: List<ItemPropertyDto> = emptyList(),
) {
    fun toModel(): Item = Item(
        id = id,
        title = title,
        description = description,
        categories = categories.map { it.toModel() },
        properties = properties.map { it.toModel() }
    )
}

fun Item.toDto(): ItemDto = ItemDto(
    id = id,
    title = title,
    description = description,
    categories = categories.map { it.toDto() },
    properties = properties.map { it.toDto() },
)

data class ItemPropertyDto(
    val id: Long? = null,
    val itemId: Long,
    val property: PropertyDto,
    val value: String,
) {
    fun toModel(): ItemProperty = ItemProperty(
        id = id,
        itemId = itemId,
        property = property.toModel(),
        value = value,
    )
}

fun ItemProperty.toDto(): ItemPropertyDto = ItemPropertyDto(
    id = id,
    itemId = itemId,
    property = property.toDto(),
    value = value,
)

data class ItemCategoryDto(
    val id: Long? = null,
    val itemId: Long,
    val category: CategoryDto,
    val parentCategory: CategoryDto? = null,
    val value: String,
) {
    fun toModel(): ItemCategory = ItemCategory(
        id = id,
        itemId = itemId,
        category = category.toModel(),
        parentCategory = parentCategory?.toModel(),
        value = value,
    )
}

fun ItemCategory.toDto(): ItemCategoryDto = ItemCategoryDto(
    id = id,
    itemId = itemId,
    category = category.toDto(),
    parentCategory = parentCategory?.toDto(),
    value = value,
)

data class PatchPropertyDto(
    val propertyId: Long,
    val propertyValue: String,
)