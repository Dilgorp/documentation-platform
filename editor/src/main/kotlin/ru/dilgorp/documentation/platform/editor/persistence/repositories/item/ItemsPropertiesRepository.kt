package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemPropertyEntity

interface ItemsPropertiesRepository : CrudRepository<ItemPropertyEntity, Long> {
    fun findByItemIdAndPropertyId(itemId: Long, propertyId: Long): ItemPropertyEntity?
    fun findAllByItemId(itemId: Long): List<ItemPropertyEntity>
}