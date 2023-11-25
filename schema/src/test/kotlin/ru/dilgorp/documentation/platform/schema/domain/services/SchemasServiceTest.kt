package ru.dilgorp.documentation.platform.schema.domain.services

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.schema.base.BaseServiceTest
import ru.dilgorp.documentation.platform.schema.domain.converters.toModel
import ru.dilgorp.documentation.platform.schema.persistence.data.schemaEntity
import java.util.*

internal class SchemasServiceTest : BaseServiceTest() {
    @Autowired
    private lateinit var schemasService: SchemasService

    @Test
    fun `findSchemas - happy path`() {
        val schemas = listOf(
            schemaEntity(),
            schemaEntity(),
            schemaEntity(),
        )

        whenever(schemasRepository.findAll()).thenReturn(schemas)

        val result = schemasService.findSchemas()

        assertEquals(schemas.map { it.toModel() }, result)
        verify(schemasRepository).findAll()
    }

    @Test
    fun `findSchemaById - happy path`() {
        val id = randomId()
        val schema = schemaEntity(id = id)

        whenever(schemasRepository.findById(id)).thenReturn(Optional.of(schema))

        val result = schemasService.findSchemaById(id)

        assertEquals(schema.toModel(), result)
        verify(schemasRepository).findById(id)
    }
}