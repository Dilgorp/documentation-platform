package ru.dilgorp.documentation.platform.editor.persistence.data.item

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemPropertyEntity

fun itemPropertyEntity(
    id: Long? = randomId(),
    itemId: Long = randomId(),
    propertyId: Long = randomId(),
    propertyValue: String = "Property value",
): ItemPropertyEntity = ItemPropertyEntity(
    id = id,
    itemId = itemId,
    propertyId = propertyId,
    propertyValue = propertyValue,
)