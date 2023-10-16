package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.Item

data class SchemaListDto(
    val id: Long,
    val title: String,
)

data class SchemaItemDto(
    val id: Long? = null,
    val schemaId: Long,
    val item: Item,
)

data class SchemaDto(
    val id: Long? = null,
    val title: String,
)
