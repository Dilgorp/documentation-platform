package ru.dilgorp.documentation.platform.editor.persistence.entities.item

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("items_categories")
data class ItemCategoryEntity(
    @get:Id
    @Column("id")
    var id: Long? = null,

    @Column("item_id")
    var itemId: Long,

    @Column("parent_category_id")
    var parentCategoryId: Long? = null,

    @Column("category_id")
    var categoryId: Long,

    @Column("category_value")
    var categoryValue: String,
)
