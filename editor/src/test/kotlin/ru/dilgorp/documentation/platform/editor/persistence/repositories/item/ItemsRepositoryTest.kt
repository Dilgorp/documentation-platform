package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.item.itemEntity

class ItemsRepositoryTest : BaseRepositoryTest() {
    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @Test
    fun `save - findBy - happy path`() {
        val entity = itemEntity(id = null)

        val savedEntity = itemsRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundedEntity = itemsRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundedEntity)
    }

    @Test
    fun `findAllByTitle - happy path`() {
        val title = randomUuid()
        val entities = listOf(
            itemEntity(
                id = null,
                title = title,
            ),
            itemEntity(
                id = null,
                title = title,
            )
        ).map { itemsRepository.save(it) }

        itemsRepository.save(itemEntity(id = null))

        val result = itemsRepository.findAllByTitle(title)
        assertEquals(entities, result)
    }

    @Test
    fun `findAllByTitle - no items were found`() {
        val title = randomUuid()
        listOf(
            itemEntity(
                id = null,
                title = randomUuid(),
            ),
            itemEntity(
                id = null,
                title = randomUuid(),
            )
        ).map { itemsRepository.save(it) }

        val result = itemsRepository.findAllByTitle(title)
        assertTrue(result.isEmpty())
    }
}