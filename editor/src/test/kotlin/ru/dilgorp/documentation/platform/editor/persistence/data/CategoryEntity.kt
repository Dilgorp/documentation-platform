package ru.dilgorp.documentation.platform.editor.persistence.data

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.entities.CategoryEntity

fun categoryEntity(
    id: Long? = randomId(),
    title: String = "Category"
): CategoryEntity = CategoryEntity(
    id = id,
    title = title,
)