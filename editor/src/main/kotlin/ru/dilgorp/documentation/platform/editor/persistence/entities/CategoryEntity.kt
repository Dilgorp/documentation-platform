package ru.dilgorp.documentation.platform.editor.persistence.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "categories")
data class CategoryEntity(
    @get:Id
    @Column("id")
    var id: Long? = null,

    @Column("title")
    var title: String = "",
)