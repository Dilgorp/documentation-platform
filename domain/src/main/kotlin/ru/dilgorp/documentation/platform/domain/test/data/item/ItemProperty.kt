package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.models.ItemProperty
import ru.dilgorp.documentation.platform.domain.models.PatchItemProperty
import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

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

fun patchItemProperty(
    id: Long? = randomId(),
    itemId: Long = randomId(),
    propertyId: Long = randomId(),
    value: String = randomUuid(),
): PatchItemProperty = PatchItemProperty(
    id = id,
    itemId = itemId,
    propertyId = propertyId,
    value = value,
)