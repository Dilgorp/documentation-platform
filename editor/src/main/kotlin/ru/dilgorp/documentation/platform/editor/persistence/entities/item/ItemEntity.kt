package ru.dilgorp.documentation.platform.editor.persistence.entities.item

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("items")
data class ItemEntity(
    @get:Id
    @Column("id")
    var id: Long? = null,

    @Column("title")
    var title: String = "",

    @Column("description")
    var description: String? = null,
)
