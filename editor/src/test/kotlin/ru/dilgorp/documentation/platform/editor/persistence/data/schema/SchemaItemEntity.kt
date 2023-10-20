package ru.dilgorp.documentation.platform.editor.persistence.data.schema

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaItemEntity

fun schemaItemEntity(
    id: Long? = randomId(),
    schemaId: Long = randomId(),
    itemId: Long = randomId(),
): SchemaItemEntity = SchemaItemEntity(
    id = id,
    schemaId = schemaId,
    itemId = itemId,
)