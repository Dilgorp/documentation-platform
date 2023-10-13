package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.item.ItemCategoryEntity

interface ItemsCategoriesRepository : CrudRepository<ItemCategoryEntity, Long>