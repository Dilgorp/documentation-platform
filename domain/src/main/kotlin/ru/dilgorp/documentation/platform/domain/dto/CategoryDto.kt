package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.Category

data class CategoryDto(
    val id: Long? = null,
    val title: String,
) {
    fun toModel(): Category = Category(
        id = id,
        title = title,
    )
}

fun Category.toDto(): CategoryDto = CategoryDto(
    id = id,
    title = title,
)
