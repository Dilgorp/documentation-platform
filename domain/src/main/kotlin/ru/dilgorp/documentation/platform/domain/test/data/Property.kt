package ru.dilgorp.documentation.platform.domain.test.data

import ru.dilgorp.documentation.platform.domain.dto.PatchPropertyDto
import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

fun property(
    id: Long? = randomId(),
    title: String = "Property"
): Property = Property(
    id = id,
    title = title,
)

fun patchPropertyDto(
    title: String = randomUuid(),
): PatchPropertyDto = PatchPropertyDto(
    title = title,
)