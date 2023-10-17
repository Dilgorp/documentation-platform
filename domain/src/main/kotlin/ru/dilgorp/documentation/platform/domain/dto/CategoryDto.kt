package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.Category

data class CategoryDto(
    val id: Long? = null,
    val title: String,
)

fun Category.toDto(): CategoryDto = CategoryDto(
    id = id,
    title = title,
)
