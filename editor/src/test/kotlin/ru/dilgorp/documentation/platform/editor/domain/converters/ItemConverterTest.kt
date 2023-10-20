package ru.dilgorp.documentation.platform.editor.domain.converters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.domain.test.data.item.item
import ru.dilgorp.documentation.platform.domain.test.data.item.itemCategory
import ru.dilgorp.documentation.platform.domain.test.data.item.itemProperty
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.domain.test.utils.diffFromRandom
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemCategoryEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemPropertyEntity

internal class ItemConverterTest {

    @Test
    fun `ItemEntity - toModel - happy path`() {
        val entity = itemEntity(
            id = 1,
            title = "Title",
            description = "Description"
        )

        val model = item(
            id = 1,
            title = "Title",
            description = "Description"
        )

        assertEquals(model, entity.toModel())
    }

    @Test
    fun `ItemEntity - toModel with parameters - happy path`() {
        val itemId = randomId()

        val itemProperties = listOf(itemProperty(itemId = itemId))
        val itemCategories = listOf(itemCategory(itemId = itemId))

        val item = item(
            id = itemId,
            title = "Title",
            description = "Description",
            properties = itemProperties,
            categories = itemCategories,
        )

        val entity = itemEntity(
            id = itemId,
            title = "Title",
            description = "Description",
        )

        assertEquals(item, entity.toModel(itemCategories, itemProperties))
    }

    @Test
    fun `ItemEntity - toModel with parameters - mixed list of properties and categories`() {
        val itemId = randomId()
        val otherId = itemId.diffFromRandom()

        val itemProperties = listOf(
            itemProperty(itemId = itemId),
            itemProperty(itemId = otherId),
            itemProperty(itemId = otherId),
        )
        val itemCategories = listOf(
            itemCategory(itemId = itemId),
            itemCategory(itemId = otherId),
            itemCategory(itemId = otherId),
        )

        val item = item(
            id = itemId,
            title = "Title",
            description = "Description",
            properties = itemProperties.filter { it.itemId == itemId },
            categories = itemCategories.filter { it.itemId == itemId },
        )

        val entity = itemEntity(
            id = itemId,
            title = "Title",
            description = "Description",
        )

        assertEquals(item, entity.toModel(itemCategories, itemProperties))
    }

    @Test
    fun `ItemCategoryEntity - toModel - happy path`() {
        val category = category()
        val parentCategory = category()

        val id = randomId()
        val itemId = randomId()
        val categoryValue = "Category value"

        val itemCategory = itemCategory(
            id = id,
            itemId = itemId,
            category = category,
            parentCategory = parentCategory,
            value = categoryValue,
        )
        val itemCategoryEntity = itemCategoryEntity(
            id = id,
            itemId = itemId,
            categoryId = category.id!!,
            parentCategoryId = parentCategory.id!!,
            categoryValue = categoryValue,
        )

        assertEquals(itemCategory, itemCategoryEntity.toModel(category, parentCategory))
    }

    @Test
    fun `ItemCategoryEntity - toModel - invalid id in category`() {
        val category = category()
        val parentCategory = category()

        val id = randomId()
        val itemId = randomId()
        val categoryValue = "Category value"

        val itemCategoryEntity = itemCategoryEntity(
            id = id,
            itemId = itemId,
            categoryId = category.id!!.diffFromRandom(),
            parentCategoryId = parentCategory.id!!,
            categoryValue = categoryValue,
        )

        assertThrows<IllegalArgumentException> {
            itemCategoryEntity.toModel(category, parentCategory)
        }
    }

    @Test
    fun `ItemCategoryEntity - toModel - invalid id in parent category`() {
        val category = category()
        val parentCategory = category()

        val id = randomId()
        val itemId = randomId()
        val categoryValue = "Category value"

        val itemCategoryEntity = itemCategoryEntity(
            id = id,
            itemId = itemId,
            categoryId = category.id!!,
            parentCategoryId = parentCategory.id!!.diffFromRandom(),
            categoryValue = categoryValue,
        )

        assertThrows<IllegalArgumentException> {
            itemCategoryEntity.toModel(category, parentCategory)
        }
    }

    @Test
    fun `ItemPropertyEntity - toModel - happy path`() {
        val property = property()

        val id = randomId()
        val itemId = randomId()
        val propertyValue = "Property value"

        val itemProperty = itemProperty(
            id = id,
            itemId = itemId,
            property = property,
            value = propertyValue,
        )
        val itemPropertyEntity = itemPropertyEntity(
            id = id,
            itemId = itemId,
            propertyId = property.id!!,
            propertyValue = propertyValue,
        )

        assertEquals(itemProperty, itemPropertyEntity.toModel(property))
    }

    @Test
    fun `ItemPropertyEntity - toModel - invalid id in property`() {
        val property = property()

        val id = randomId()
        val itemId = randomId()
        val propertyValue = "Property value"

        val itemPropertyEntity = itemPropertyEntity(
            id = id,
            itemId = itemId,
            propertyId = property.id!!.diffFromRandom(),
            propertyValue = propertyValue,
        )

        assertThrows<IllegalArgumentException> {
            itemPropertyEntity.toModel(property)
        }
    }

    @Test
    fun `Item - toEntity - happy path`() {
        val entity = itemEntity(
            id = 1,
            title = "Title",
            description = "Description"
        )

        val model = item(
            id = 1,
            title = "Title",
            description = "Description"
        )

        assertEquals(entity, model.toEntity())
    }

    @Test
    fun `ItemCategory - toEntity - happy path`() {
        val category = category()
        val parentCategory = category()

        val id = randomId()
        val itemId = randomId()
        val categoryValue = "Category value"

        val itemCategory = itemCategory(
            id = id,
            itemId = itemId,
            category = category,
            parentCategory = parentCategory,
            value = categoryValue,
        )
        val itemCategoryEntity = itemCategoryEntity(
            id = id,
            itemId = itemId,
            categoryId = category.id!!,
            parentCategoryId = parentCategory.id!!,
            categoryValue = categoryValue,
        )

        assertEquals(itemCategoryEntity, itemCategory.toEntity())
    }

    @Test
    fun `ItemCategory - toEntity - category id missed`() {
        val category = category(id = null)
        val parentCategory = category()

        val id = randomId()
        val itemId = randomId()
        val categoryValue = "Category value"

        val itemCategory = itemCategory(
            id = id,
            itemId = itemId,
            category = category,
            parentCategory = parentCategory,
            value = categoryValue,
        )

        assertThrows<IllegalArgumentException> {
            itemCategory.toEntity()
        }
    }

    @Test
    fun `ItemProperty - toEntity - happy path`() {
        val property = property()

        val id = randomId()
        val itemId = randomId()
        val propertyValue = "Property value"

        val itemProperty = itemProperty(
            id = id,
            itemId = itemId,
            property = property,
            value = propertyValue,
        )
        val itemPropertyEntity = itemPropertyEntity(
            id = id,
            itemId = itemId,
            propertyId = property.id!!,
            propertyValue = propertyValue,
        )

        assertEquals(itemPropertyEntity, itemProperty.toEntity())
    }

    @Test
    fun `ItemProperty - toEntity - property id missed`() {
        val property = property(id = null)

        val id = randomId()
        val itemId = randomId()
        val propertyValue = "Property value"

        val itemProperty = itemProperty(
            id = id,
            itemId = itemId,
            property = property,
            value = propertyValue,
        )

        assertThrows<IllegalArgumentException> {
            itemProperty.toEntity()
        }
    }
}