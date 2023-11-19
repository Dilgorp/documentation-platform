package ru.dilgorp.documentation.platform.editor.persistence.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.propertyEntity

class PropertiesRepositoryTest : BaseRepositoryTest() {
    @Autowired
    private lateinit var propertiesRepository: PropertiesRepository

    @Test
    fun `save - findBy - happy path`() {
        val entity = propertyEntity(id = null)

        val savedEntity = propertiesRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundEntity = propertiesRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundEntity)
    }

    @Test
    fun `findByTitle - happy path`() {
        val entity = propertiesRepository.save(propertyEntity(id = null))

        val foundEntity = propertiesRepository.findByTitle(entity.title)

        assertEquals(entity, foundEntity)

        val nullEntity = propertiesRepository.findByTitle("{$entity.title}_${randomId()}")
        assertNull(nullEntity)
    }
}