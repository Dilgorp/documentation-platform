package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.dto.PatchCategoryItemDto
import ru.dilgorp.documentation.platform.domain.dto.PatchItemDto
import ru.dilgorp.documentation.platform.domain.dto.PatchPropertyItemDto
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

fun patchItemDto(
    title: String = randomUuid(),
    description: String? = randomUuid(),
): PatchItemDto = PatchItemDto(
    title = title,
    description = description,
)

fun patchPropertyItemDto(): PatchPropertyItemDto = PatchPropertyItemDto(
    propertyId = randomId(),
    propertyValue = randomUuid(),
)

fun patchCategoryItemDto(): PatchCategoryItemDto = PatchCategoryItemDto(
    categoryId = randomId(),
    categoryValue = randomUuid(),
)