package ru.dilgorp.documentation.platform.editor.persistence.entities.schema

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("schemas_items")
data class SchemaItemEntity(
    @get:Id
    @Column("id")
    var id: Long? = null,

    @Column("schema_id")
    var schemaId: Long,

    @Column("item_id")
    var itemId: Long,
)
