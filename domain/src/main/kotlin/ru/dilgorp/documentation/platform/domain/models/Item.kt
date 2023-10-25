package ru.dilgorp.documentation.platform.domain.models

data class Item(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val categories: List<ItemCategory> = emptyList(),
    val properties: List<ItemProperty> = emptyList(),
)

data class ItemProperty(
    val id: Long? = null,
    val itemId: Long,
    val property: Property,
    val value: String,
)

data class ItemCategory(
    val id: Long? = null,
    val itemId: Long,
    val category: Category,
    val parentCategory: Category? = null,
    val value: String,
)

data class PatchItemProperty(
    val id: Long? = null,
    val itemId: Long,
    val propertyId: Long,
    val value: String,
)

data class PatchItemCategory(
    val id: Long? = null,
    val itemId: Long,
    val categoryId: Long,
    val value: String,
)
