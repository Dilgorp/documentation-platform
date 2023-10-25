package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.ItemCategory
import ru.dilgorp.documentation.platform.domain.models.ItemProperty
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun item(
    id: Long? = randomId(),
    title: String = "Item",
    description: String? = "Item Description",
    properties: List<ItemProperty> = emptyList(),
    categories: List<ItemCategory> = emptyList(),
): Item = Item(
    id = id,
    title = title,
    description = description,
    properties = properties,
    categories = categories,
)