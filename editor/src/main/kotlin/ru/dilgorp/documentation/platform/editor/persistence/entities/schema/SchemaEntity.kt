package ru.dilgorp.documentation.platform.editor.persistence.entities.schema

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("schemas")
data class SchemaEntity(
    @get:Id
    @Column("id")
    var id: Long? = null,

    @Column("title")
    var title: String = "",

    @Column("description")
    var description: String? = null,
)
