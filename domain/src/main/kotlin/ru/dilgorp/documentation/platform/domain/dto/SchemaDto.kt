package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.*

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
    val item: ItemListDto,
)

fun SchemaItem.toDto(): SchemaItemDto = SchemaItemDto(
    id = id,
    schemaId = schemaId,
    item = item.toDto(),
)

data class FullSchemaItemDto(
    val id: Long? = null,
    val schemaId: Long,
    val item: ItemDto,
)

fun FullSchemaItem.toDto(): FullSchemaItemDto = FullSchemaItemDto(
    id = id,
    schemaId = schemaId,
    item = item.toDto(),
)

data class SchemaDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val items: List<SchemaItemDto> = emptyList(),
)

fun Schema.toDto(): SchemaDto = SchemaDto(
    id = id,
    title = title,
    description = description,
    items = items.map { it.toDto() }
)

data class FullSchemaDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val items: List<FullSchemaItemDto> = emptyList(),
)

fun FullSchema.toDto(): FullSchemaDto = FullSchemaDto(
    id = id,
    title = title,
    description = description,
    items = items.map { it.toDto() }
)

data class PatchSchemaDto(
    val title: String,
    val description: String? = null,
) {
    fun toModel(): Schema = Schema(
        title = title,
        description = description,
    )

    fun toModel(id: Long): Schema = Schema(
        id = id,
        title = title,
        description = description,
    )
}

data class PatchSchemaItemDto(
    val itemId: Long,
) {
    fun toModel(schemaId: Long): PatchSchemaItem = PatchSchemaItem(
        schemaId = schemaId,
        itemId = itemId,
    )

    fun toModel(id: Long, schemaId: Long): PatchSchemaItem = PatchSchemaItem(
        id = id,
        schemaId = schemaId,
        itemId = itemId,
    )
}