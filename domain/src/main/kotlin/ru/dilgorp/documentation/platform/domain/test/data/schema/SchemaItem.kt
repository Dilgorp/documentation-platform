package ru.dilgorp.documentation.platform.domain.test.data.schema

import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.SchemaItem
import ru.dilgorp.documentation.platform.domain.test.data.item.item
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun schemaItem(
    id: Long? = randomId(),
    schemaId: Long = randomId(),
    item: Item = item(),
): SchemaItem = SchemaItem(
    id = id,
    schemaId = schemaId,
    item = item,
)