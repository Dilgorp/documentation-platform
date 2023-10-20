package ru.dilgorp.documentation.platform.editor.persistence.data.item

import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemCategoryEntity

fun itemCategoryEntity(
    id: Long? = randomId(),
    itemId: Long = randomId(),
    categoryId: Long = randomId(),
    parentCategoryId: Long? = randomId(),
    categoryValue: String = "Category value",
): ItemCategoryEntity = ItemCategoryEntity(
    id = id,
    itemId = itemId,
    categoryId = categoryId,
    parentCategoryId = parentCategoryId,
    categoryValue = categoryValue,
)