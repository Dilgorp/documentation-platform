package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.PatchSchemaItem
import ru.dilgorp.documentation.platform.domain.models.Schema
import ru.dilgorp.documentation.platform.domain.models.SchemaItem

data class SchemaListDto(
    val id: Long,
    val title: String,
)

fun Schema.toListDto(): SchemaListDto = SchemaListDto(
    id = requireNotNull(id),
    title = title,
)

data class SchemaItemDto(
    val id: Long? = null,
    val schemaId: Long,
    val item: ItemDto,
) {
    fun toModel(): SchemaItem = SchemaItem(
        id = id,
        schemaId = schemaId,
        item = item.toModel(),
    )
}

fun SchemaItem.toDto(): SchemaItemDto = SchemaItemDto(
    id = id,
    schemaId = schemaId,
    item = item.toDto(),
)

data class SchemaDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val items: List<SchemaItemDto> = emptyList(),
) {
    fun toModel(): Schema = Schema(
        id = id,
        title = title,
        description = description,
        items = items.map { it.toModel() }
    )
}

fun Schema.toDto(): SchemaDto = SchemaDto(
    id = id,
    title = title,
    description = description,
    items = items.map { it.toDto() }
)

data class PatchSchemaItemDto(
    val id: Long? = null,
    val schemaId: Long,
    val itemId: Long,
) {
    fun toModel(): PatchSchemaItem = PatchSchemaItem(
        id = id,
        schemaId = schemaId,
        itemId = itemId,
    )
}