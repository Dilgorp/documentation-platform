package ru.dilgorp.documentation.platform.domain.test.data.schema

import ru.dilgorp.documentation.platform.domain.dto.PatchSchemaItemDto
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun patchSchemaItemDto(itemId: Long = randomId()): PatchSchemaItemDto = PatchSchemaItemDto(
    itemId = itemId,
)