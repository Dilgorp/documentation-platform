package ru.dilgorp.documentation.platform.editor.persistence.entities.item

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("items_properties")
data class ItemPropertyEntity(
    @get:Id
    @Column("id")
    var id: Long? = null,

    @Column("item_id")
    var itemId: Long,

    @Column("property_id")
    var propertyId: Long,

    @Column("property_value")
    var propertyValue: String,
)
