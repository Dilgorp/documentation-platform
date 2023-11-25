package ru.dilgorp.documentation.platform.schema.persistence.data

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.schema.persistence.entities.schema.SchemaEntity
import ru.dilgorp.documentation.platform.schema.persistence.entities.schema.SchemaItemEntity

fun schemaEntity(
    id: Long? = randomId(),
    title: String = randomUuid(),
    description: String? = randomUuid(),
    items: MutableList<SchemaItemEntity> = mutableListOf(),
): SchemaEntity = SchemaEntity().apply {
    this.id = id
    this.title = title
    this.description = description
    this.items = items
}