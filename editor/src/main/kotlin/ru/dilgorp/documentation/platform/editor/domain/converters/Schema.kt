package ru.dilgorp.documentation.platform.editor.domain.converters

import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.PatchSchemaItem
import ru.dilgorp.documentation.platform.domain.models.Schema
import ru.dilgorp.documentation.platform.domain.models.SchemaItem
import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaEntity
import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaItemEntity

fun SchemaEntity.toModel(): Schema = Schema(
    id = id,
    title = title,
    description = description,
    items = emptyList(),
)

fun SchemaEntity.toModel(schemaItems: List<SchemaItem>): Schema = Schema(
    id = id,
    title = title,
    description = description,
    items = schemaItems.filter { it.schemaId == id },
)

fun Schema.toEntity(): SchemaEntity = SchemaEntity(
    id = id,
    title = title,
    description = description,
)

fun SchemaItemEntity.toModel(item: Item): SchemaItem = SchemaItem(
    id = id,
    schemaId = schemaId,
    item = if (item.id == itemId) item else throw IllegalArgumentException("Item id and itemId are different"),
)

fun SchemaItem.toEntity(): SchemaItemEntity = SchemaItemEntity(
    id = id,
    schemaId = schemaId,
    itemId = requireNotNull(item.id),
)

fun PatchSchemaItem.toEntity(): SchemaItemEntity = SchemaItemEntity(
    id = id,
    schemaId = schemaId,
    itemId = itemId,
)