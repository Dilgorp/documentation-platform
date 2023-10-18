package ru.dilgorp.documentation.platform.editor.domain.converters

import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.editor.persistence.entities.PropertyEntity

fun PropertyEntity.toModel(): Property = Property(
    id = id,
    title = title,
)

fun Property.toEntity(): PropertyEntity = PropertyEntity(
    id = id,
    title = title,
)
