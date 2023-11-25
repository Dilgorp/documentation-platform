package ru.dilgorp.documentation.platform.domain.models

data class Schema(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val items: List<SchemaItem> = emptyList(),
)

data class FullSchema(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val items: List<FullSchemaItem> = emptyList(),
)

data class SchemaItem(
    val id: Long? = null,
    val schemaId: Long,
    val item: ItemList,
)

data class FullSchemaItem(
    val id: Long? = null,
    val schemaId: Long,
    val item: Item,
)

data class PatchSchemaItem(
    val id: Long? = null,
    val schemaId: Long,
    val itemId: Long,
)