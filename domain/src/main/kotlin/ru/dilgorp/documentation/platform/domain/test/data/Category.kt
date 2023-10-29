package ru.dilgorp.documentation.platform.domain.test.data

import ru.dilgorp.documentation.platform.domain.dto.PatchCategoryDto
import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

fun category(
    id: Long? = randomId(),
    title: String = "Category"
): Category = Category(
    id = id,
    title = title,
)

fun patchCategoryDto(
    title: String = randomUuid(),
): PatchCategoryDto = PatchCategoryDto(
    title = title,
)