package ru.dilgorp.documentation.platform.domain.test.data.schema

import ru.dilgorp.documentation.platform.domain.models.ItemList
import ru.dilgorp.documentation.platform.domain.models.PatchSchemaItem
import ru.dilgorp.documentation.platform.domain.models.SchemaItem
import ru.dilgorp.documentation.platform.domain.test.data.item.itemList
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun schemaItem(
    id: Long? = randomId(),
    schemaId: Long = randomId(),
    item: ItemList = itemList(),
): SchemaItem = SchemaItem(
    id = id,
    schemaId = schemaId,
    item = item,
)

fun patchSchemaItem(
    id: Long? = randomId(),
    schemaId: Long = randomId(),
    itemId: Long = randomId(),
): PatchSchemaItem = PatchSchemaItem(
    id = id,
    schemaId = schemaId,
    itemId = itemId,
)