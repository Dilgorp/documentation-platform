package ru.dilgorp.documentation.platform.editor.persistence.data

import ru.dilgorp.documentation.platform.editor.persistence.entities.PropertyEntity
import ru.dilgorp.documentation.platform.editor.utils.randomId

fun propertyEntity(
    id: Long? = randomId(),
    title: String = "Property"
): PropertyEntity = PropertyEntity(
    id = id,
    title = title,
)