package ru.dilgorp.documentation.platform.schema.persistence.entities.item

import jakarta.persistence.*
import ru.dilgorp.documentation.platform.schema.persistence.entities.PropertyEntity

@Table(name = "items_properties")
@Entity
class ItemPropertyEntity {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = null

    @Column(name = "item_id", insertable = false, updatable = false)
    var itemId: Long? = null

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var item: ItemEntity

    @OneToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var property: PropertyEntity

    @Column(name = "property_value", insertable = false, updatable = false)
    lateinit var propertyValue: String
}
