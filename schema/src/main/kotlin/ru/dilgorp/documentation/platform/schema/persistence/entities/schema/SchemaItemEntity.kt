package ru.dilgorp.documentation.platform.schema.persistence.entities.schema

import jakarta.persistence.*
import ru.dilgorp.documentation.platform.schema.persistence.entities.item.ItemEntity

@Table(name = "schemas_items")
@Entity
class SchemaItemEntity {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = null

    @Column(name = "schema_id", insertable = false, updatable = false)
    var schemaId: Long? = null

    @ManyToOne
    @JoinColumn(name = "schema_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var schema: SchemaEntity

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var item: ItemEntity
}
