package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.dto.PatchCategoryDto
import ru.dilgorp.documentation.platform.domain.dto.PatchPropertyDto
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

fun patchPropertyDto(): PatchPropertyDto = PatchPropertyDto(
    propertyId = randomId(),
    propertyValue = randomUuid(),
)

fun patchCategoryDto(): PatchCategoryDto = PatchCategoryDto(
    categoryId = randomId(),
    categoryValue = randomUuid(),
)