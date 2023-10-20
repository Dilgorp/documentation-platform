package ru.dilgorp.documentation.platform.editor.persistence.data.schema

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaEntity

fun schemaEntity(
    id: Long? = randomId(),
    title: String = "Schema",
    description: String? = "Schema Description",
): SchemaEntity = SchemaEntity(
    id = id,
    title = title,
    description = description,
)