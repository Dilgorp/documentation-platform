package ru.dilgorp.documentation.platform.schema.domain.converters

import ru.dilgorp.documentation.platform.domain.models.FullSchema
import ru.dilgorp.documentation.platform.domain.models.FullSchemaItem
import ru.dilgorp.documentation.platform.schema.persistence.entities.schema.SchemaEntity
import ru.dilgorp.documentation.platform.schema.persistence.entities.schema.SchemaItemEntity

fun SchemaEntity.toModel(): FullSchema = FullSchema(
    id = id,
    title = title,
    description = description,
    items = items.map { it.toModel() }
)

fun SchemaItemEntity.toModel(): FullSchemaItem = FullSchemaItem(
    id = id,
    schemaId = requireNotNull(schemaId),
    item = item.toModel(),
)