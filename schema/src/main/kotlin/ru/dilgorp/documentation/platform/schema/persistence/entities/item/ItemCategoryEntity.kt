package ru.dilgorp.documentation.platform.schema.persistence.entities.item

import jakarta.persistence.*
import ru.dilgorp.documentation.platform.schema.persistence.entities.CategoryEntity

@Table(name = "items_categories")
@Entity
class ItemCategoryEntity {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = null

    @Column(name = "item_id", insertable = false, updatable = false)
    var itemId: Long? = null

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var item: ItemEntity

    @OneToOne
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    var parentCategory: CategoryEntity? = null

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var category: CategoryEntity

    @Column(name = "category_value", insertable = false, updatable = false)
    lateinit var categoryValue: String
}
