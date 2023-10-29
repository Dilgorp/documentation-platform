package ru.dilgorp.documentation.platform.domain.test.data.schema

import ru.dilgorp.documentation.platform.domain.dto.PatchSchemaDto
import ru.dilgorp.documentation.platform.domain.dto.PatchSchemaItemDto
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

fun patchSchemaDto(
    title: String = randomUuid(),
    description: String? = randomUuid(),
): PatchSchemaDto = PatchSchemaDto(
    title = title,
    description = description,
)

fun patchSchemaItemDto(itemId: Long = randomId()): PatchSchemaItemDto = PatchSchemaItemDto(
    itemId = itemId,
)