package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
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

    @Test
    fun `findByItemIdAndCategoryId - happy path`() {
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

        val foundEntity = itemsCategoriesRepository.findByItemIdAndCategoryId(itemEntity.id!!, categoryEntity.id!!)
        assertEquals(savedEntity, foundEntity)
    }

    @Test
    fun `findByItemIdAndCategoryId - entity not found`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val categoryEntity = categoriesRepository.save(categoryEntity(id = null))

        val foundEntity = itemsCategoriesRepository.findByItemIdAndCategoryId(itemEntity.id!!, categoryEntity.id!!)
        assertNull(foundEntity)
    }

    @Test
    fun `findAllByItemId - happy path`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val categoryEntity = categoriesRepository.save(categoryEntity(id = null))

        val itemEntity2 = itemsRepository.save(itemEntity(id = null))
        val categoryEntity2 = categoriesRepository.save(categoryEntity(id = null))

        val entities = itemsCategoriesRepository.saveAll(
            listOf(
                itemCategoryEntity(
                    id = null,
                    itemId = itemEntity.id!!,
                    categoryId = categoryEntity.id!!,
                    parentCategoryId = null,
                ),
                itemCategoryEntity(
                    id = null,
                    itemId = itemEntity.id!!,
                    categoryId = categoryEntity.id!!,
                    parentCategoryId = null,
                ),
                itemCategoryEntity(
                    id = null,
                    itemId = itemEntity.id!!,
                    categoryId = categoryEntity.id!!,
                    parentCategoryId = null,
                ),
                itemCategoryEntity(
                    id = null,
                    itemId = itemEntity2.id!!,
                    categoryId = categoryEntity2.id!!,
                    parentCategoryId = null,
                ),
            )
        )

        val foundEntities = itemsCategoriesRepository.findAllByItemId(itemEntity.id!!)

        assertEquals(
            entities.filter { it.itemId == itemEntity.id!! }.sortedBy { it.id },
            foundEntities.sortedBy { it.id }
        )
    }
}