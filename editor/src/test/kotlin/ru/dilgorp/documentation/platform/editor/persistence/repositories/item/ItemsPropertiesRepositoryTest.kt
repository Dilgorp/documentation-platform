package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemPropertyEntity
import ru.dilgorp.documentation.platform.editor.persistence.data.propertyEntity
import ru.dilgorp.documentation.platform.editor.persistence.repositories.PropertiesRepository

class ItemsPropertiesRepositoryTest : BaseRepositoryTest() {
    @Autowired
    private lateinit var itemsPropertiesRepository: ItemsPropertiesRepository

    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @Autowired
    private lateinit var propertiesRepository: PropertiesRepository

    @Test
    fun `save - findBy - happy path`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val propertyEntity = propertiesRepository.save(propertyEntity(id = null))

        val entity = itemPropertyEntity(
            id = null,
            itemId = itemEntity.id!!,
            propertyId = propertyEntity.id!!,
        )

        val savedEntity = itemsPropertiesRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundedEntity = itemsPropertiesRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundedEntity)
    }

    @Test
    fun `findByItemIdAndPropertyId - happy path`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val propertyEntity = propertiesRepository.save(propertyEntity(id = null))

        val entity = itemPropertyEntity(
            id = null,
            itemId = itemEntity.id!!,
            propertyId = propertyEntity.id!!,
        )

        val savedEntity = itemsPropertiesRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundEntity = itemsPropertiesRepository.findByItemIdAndPropertyId(itemEntity.id!!, propertyEntity.id!!)
        assertEquals(savedEntity, foundEntity)
    }

    @Test
    fun `findByItemIdAndPropertyId - entity not found`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val propertyEntity = propertiesRepository.save(propertyEntity(id = null))

        val foundEntity = itemsPropertiesRepository.findByItemIdAndPropertyId(itemEntity.id!!, propertyEntity.id!!)
        assertNull(foundEntity)
    }

    @Test
    fun `findAllByItemId - happy path`() {
        val itemEntity = itemsRepository.save(itemEntity(id = null))
        val propertyEntity = propertiesRepository.save(propertyEntity(id = null))

        val itemEntity2 = itemsRepository.save(itemEntity(id = null))
        val propertyEntity2 = propertiesRepository.save(propertyEntity(id = null))

        val entities = itemsPropertiesRepository.saveAll(
            listOf(
                itemPropertyEntity(
                    id = null,
                    itemId = itemEntity.id!!,
                    propertyId = propertyEntity.id!!,
                ),
                itemPropertyEntity(
                    id = null,
                    itemId = itemEntity.id!!,
                    propertyId = propertyEntity.id!!,
                ),
                itemPropertyEntity(
                    id = null,
                    itemId = itemEntity.id!!,
                    propertyId = propertyEntity.id!!,
                ),
                itemPropertyEntity(
                    id = null,
                    itemId = itemEntity2.id!!,
                    propertyId = propertyEntity2.id!!,
                ),
            )
        )

        val foundEntities = itemsPropertiesRepository.findAllByItemId(itemEntity.id!!)

        assertEquals(
            entities.filter { it.itemId == itemEntity.id!! }.sortedBy { it.id },
            foundEntities.sortedBy { it.id },
        )
    }
}