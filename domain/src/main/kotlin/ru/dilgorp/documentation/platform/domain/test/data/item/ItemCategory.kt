package ru.dilgorp.documentation.platform.domain.test.data.item

import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.domain.models.ItemCategory
import ru.dilgorp.documentation.platform.domain.models.PatchItemCategory
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid

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

fun patchItemCategory(
    id: Long? = randomId(),
    itemId: Long = randomId(),
    categoryId: Long = randomId(),
    parentCategoryId: Long = randomId(),
    value: String = randomUuid(),
): PatchItemCategory = PatchItemCategory(
    id = id,
    itemId = itemId,
    categoryId = categoryId,
    parentCategoryId = parentCategoryId,
    value = value,
)