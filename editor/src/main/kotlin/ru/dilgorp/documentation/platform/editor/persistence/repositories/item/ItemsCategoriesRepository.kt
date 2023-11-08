package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemCategoryEntity

interface ItemsCategoriesRepository : CrudRepository<ItemCategoryEntity, Long> {
    fun findByItemIdAndCategoryId(itemId: Long, categoryId: Long): ItemCategoryEntity?
    fun findAllByItemId(itemId: Long): List<ItemCategoryEntity>
}