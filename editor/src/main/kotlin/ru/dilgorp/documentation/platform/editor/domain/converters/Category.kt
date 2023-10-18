package ru.dilgorp.documentation.platform.editor.domain.converters

import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.editor.persistence.entities.CategoryEntity

fun CategoryEntity.toModel(): Category = Category(
    id = id,
    title = title,
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    id = id,
    title = title,
)