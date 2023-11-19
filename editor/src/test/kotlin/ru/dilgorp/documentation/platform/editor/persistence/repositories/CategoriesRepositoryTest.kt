package ru.dilgorp.documentation.platform.editor.persistence.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.categoryEntity

class CategoriesRepositoryTest : BaseRepositoryTest() {
    @Autowired
    private lateinit var categoryRepository: CategoriesRepository

    @Test
    fun `save - findBy - happy path`() {
        val entity = categoryEntity(id = null)

        val savedEntity = categoryRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundEntity = categoryRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundEntity)
    }

    @Test
    fun `findByTitle - happy path`() {
        val title = randomUuid()

        val entity = categoryRepository.save(
            categoryEntity(
                id = null,
                title = title,
            )
        )

        val result = categoryRepository.findByTitle(title)
        assertEquals(entity, result)

        val nullEntity = categoryRepository.findByTitle(randomUuid())
        assertNull(nullEntity)
    }
}