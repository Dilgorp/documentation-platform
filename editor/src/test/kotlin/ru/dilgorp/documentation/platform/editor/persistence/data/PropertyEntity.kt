package ru.dilgorp.documentation.platform.editor.persistence.data

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.entities.PropertyEntity

fun propertyEntity(
    id: Long? = randomId(),
    title: String = "Property"
): PropertyEntity = PropertyEntity(
    id = id,
    title = title,
)