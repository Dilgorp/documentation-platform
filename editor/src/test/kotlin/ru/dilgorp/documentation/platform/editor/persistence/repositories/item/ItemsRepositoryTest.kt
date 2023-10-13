package ru.dilgorp.documentation.platform.editor.persistence.repositories.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
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
}