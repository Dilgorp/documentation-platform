package ru.dilgorp.documentation.platform.editor.persistence.data.item

import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemEntity
import ru.dilgorp.documentation.platform.editor.utils.randomId

fun itemEntity(
    id: Long? = randomId(),
    title: String = "Item",
    description: String? = "Item Description",
): ItemEntity = ItemEntity(
    id = id,
    title = title,
    description = description,
)