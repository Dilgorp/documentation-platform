package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemEntity

interface ItemsRepository : CrudRepository<ItemEntity, Long> {
    fun findAllByTitle(title: String): List<ItemEntity>
}