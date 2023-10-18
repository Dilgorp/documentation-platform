package ru.dilgorp.documentation.platform.domain.test.data

import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun property(
    id: Long? = randomId(),
    title: String = "Property"
): Property = Property(
    id = id,
    title = title,
)