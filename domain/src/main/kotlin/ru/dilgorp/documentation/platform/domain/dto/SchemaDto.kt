package ru.dilgorp.documentation.platform.domain.dto

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
)

fun SchemaItem.toDto(): SchemaItemDto = SchemaItemDto(
    id = id,
    schemaId = schemaId,
    item = item.toDto(),
)

data class SchemaDto(
    val id: Long? = null,
    val title: String,
    val items: List<SchemaItemDto> = emptyList(),
)

fun Schema.toDto(): SchemaDto = SchemaDto(
    id = id,
    title = title,
    items = items.map { it.toDto() }
)