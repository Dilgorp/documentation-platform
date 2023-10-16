package ru.dilgorp.documentation.platform.domain.models

data class Schema(
    val id: Long? = null,
    val title: String,
    val items: List<SchemaItem> = emptyList(),
)

data class SchemaItem(
    val id: Long? = null,
    val schemaId: Long,
    val item: Item,
)