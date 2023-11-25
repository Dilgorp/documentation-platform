package ru.dilgorp.documentation.platform.schema.domain.converters

import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.schema.persistence.entities.CategoryEntity

fun CategoryEntity.toModel(): Category = Category(
    id = id,
    title = title,
)