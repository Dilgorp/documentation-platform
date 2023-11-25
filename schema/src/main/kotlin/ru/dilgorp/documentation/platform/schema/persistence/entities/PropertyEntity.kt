package ru.dilgorp.documentation.platform.schema.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "properties")
@Entity
class PropertyEntity(
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    var id: Long? = null,

    @Column(name = "title", insertable = false, updatable = false)
    var title: String = "",
)
