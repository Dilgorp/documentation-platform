package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.models.ItemProperty
import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun itemProperty(
    id: Long? = randomId(),
    itemId: Long = randomId(),
    property: Property = property(),
    value: String = "Property value",
): ItemProperty = ItemProperty(
    id = id,
    itemId = itemId,
    property = property,
    value = value,
)