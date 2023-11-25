package ru.dilgorp.documentation.platform.schema.persistence.entities.item

import jakarta.persistence.*

@Table(name = "items")
@Entity
class ItemEntity {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = null

    @Column(name = "title", insertable = false, updatable = false)
    lateinit var title: String

    @Column(name = "description", insertable = false, updatable = false)
    var description: String? = null

    @OneToMany(mappedBy = "item")
    var categories: MutableList<ItemCategoryEntity> = mutableListOf()

    @OneToMany(mappedBy = "item")
    var properties: MutableList<ItemPropertyEntity> = mutableListOf()
}
