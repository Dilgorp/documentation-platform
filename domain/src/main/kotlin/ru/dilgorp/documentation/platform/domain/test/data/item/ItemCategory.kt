package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.domain.models.ItemCategory
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.domain.test.utils.randomId

fun itemCategory(
    id: Long? = randomId(),
    itemId: Long = randomId(),
    category: Category = category(),
    parentCategory: Category? = null,
    value: String = "Category value",
): ItemCategory = ItemCategory(
    id = id,
    itemId = itemId,
    category = category,
    parentCategory = parentCategory,
    value = value,
)