package ru.dilgorp.documentation.platform.schema.persistence.entities.schema

import jakarta.persistence.*

@Table(name = "schemas")
@Entity
class SchemaEntity {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = null

    @Column(name = "title", insertable = false, updatable = false)
    lateinit var title: String

    @Column(name = "description", insertable = false, updatable = false)
    var description: String? = null

    @OneToMany(mappedBy = "schema")
    var items: MutableList<SchemaItemEntity> = mutableListOf()
}
