package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.categoryEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemCategoryEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemEntity
import ru.dilgorp.documentation.platform.editor.persistence.repositories.CategoriesRepository

class ItemsCategoriesRepositoryTest : BaseRepositoryTest() {

    @Autowired
    private lateinit var itemsCategoriesRepository: ItemsCategoriesRepository

    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @Autowired
    private lateinit var categoriesRepository: CategoriesRepository

    @Test
    fun `save - findBy - happy path`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val categoryEntity = categoriesRepository.save(categoryEntity(id = null))

        val entity = itemCategoryEntity(
            id = null,
            itemId = itemEntity.id!!,
            categoryId = categoryEntity.id!!,
            parentCategoryId = null,
        )

        val savedEntity = itemsCategoriesRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundedEntity = itemsCategoriesRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundedEntity)
    }
}