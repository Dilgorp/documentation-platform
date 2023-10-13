package ru.dilgorp.documentation.platform.editor.persistence.data.schema

import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaEntity
import ru.dilgorp.documentation.platform.editor.utils.randomId

fun schemaEntity(
    id: Long? = randomId(),
    title: String = "Schema",
    description: String? = "Schema Description",
): SchemaEntity = SchemaEntity(
    id = id,
    title = title,
    description = description,
)