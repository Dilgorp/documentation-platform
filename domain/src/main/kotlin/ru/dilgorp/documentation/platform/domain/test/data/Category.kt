package ru.dilgorp.documentation.platform.domain.test.data

import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun category(
    id: Long? = randomId(),
    title: String = "Category"
): Category = Category(
    id = id,
    title = title,
)