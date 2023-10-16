package ru.dilgorp.documentation.platform.domain.dto

data class SchemaListDto(
    val id: Long,
    val title: String,
)

data class SchemaItemDto(
    val id: Long? = null,
    val schemaId: Long,
    val item: ItemDto,
)

data class SchemaDto(
    val id: Long? = null,
    val title: String,
    val items: List<SchemaItemDto> = emptyList(),
)
