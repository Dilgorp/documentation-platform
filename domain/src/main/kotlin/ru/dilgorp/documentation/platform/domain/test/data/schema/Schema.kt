package ru.dilgorp.documentation.platform.domain.test.data.schema

import ru.dilgorp.documentation.platform.domain.models.Schema
import ru.dilgorp.documentation.platform.domain.models.SchemaItem
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun schema(
    id: Long? = randomId(),
    title: String = "Schema",
    description: String? = "Schema Description",
    items: List<SchemaItem> = listOf(schemaItem()),
): Schema = Schema(
    id = id,
    title = title,
    description = description,
    items = items
)