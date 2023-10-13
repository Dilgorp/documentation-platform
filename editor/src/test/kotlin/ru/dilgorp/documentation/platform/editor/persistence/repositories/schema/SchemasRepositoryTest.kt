package ru.dilgorp.documentation.platform.editor.persistence.repositories.schema

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.editor.base.BaseRepositoryTest
import ru.dilgorp.documentation.platform.editor.persistence.data.schema.schemaEntity

class SchemasRepositoryTest : BaseRepositoryTest() {
    @Autowired
    private lateinit var schemasRepository: SchemasRepository

    @Test
    fun `save - findBy - happy path`() {
        val entity = schemaEntity(id = null)

        val savedEntity = schemasRepository.save(entity)

        assertEquals(entity.copy(id = savedEntity.id), savedEntity)

        val foundedEntity = schemasRepository.findById(savedEntity.id!!).get()
        assertEquals(savedEntity, foundedEntity)
    }
}