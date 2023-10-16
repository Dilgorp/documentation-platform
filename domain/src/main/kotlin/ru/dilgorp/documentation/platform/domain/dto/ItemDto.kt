package ru.dilgorp.documentation.platform.domain.dto

data class ItemListDto(
    val id: Long,
    val title: String,
)

data class ItemDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val categories: List<ItemCategoryDto> = emptyList(),
    val properties: List<ItemPropertyDto> = emptyList(),
)

data class ItemPropertyDto(
    val id: Long? = null,
    val itemId: Long,
    val property: PropertyDto,
    val value: String,
)

data class ItemCategoryDto(
    val id: Long? = null,
    val itemId: Long,
    val category: CategoryDto,
    val parentCategory: CategoryDto? = null,
    val value: String,
)
