package ru.dilgorp.documentation.platform.schema.domain.converters

import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.schema.persistence.entities.PropertyEntity

fun PropertyEntity.toModel(): Property = Property(
    id = id,
    title = title,
)