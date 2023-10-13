package ru.dilgorp.documentation.platform.editor.persistence.data

import ru.dilgorp.documentation.platform.editor.persistence.entities.CategoryEntity
import ru.dilgorp.documentation.platform.editor.utils.randomId

fun categoryEntity(
    id: Long? = randomId(),
    title: String = "Category"
): CategoryEntity = CategoryEntity(
    id = id,
    title = title,
)