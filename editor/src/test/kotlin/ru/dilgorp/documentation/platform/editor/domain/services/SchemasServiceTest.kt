package ru.dilgorp.documentation.platform.editor.domain.services

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import ru.dilgorp.documentation.platform.domain.test.data.schema.schema
import ru.dilgorp.documentation.platform.domain.test.utils.randomId
import ru.dilgorp.documentation.platform.editor.base.BaseServiceTest
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import java.util.*

internal class SchemasServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var schemasService: SchemasService

    @Test
    fun `save - happy path`() {
        val model = schema(items = emptyList())
        val entity = model.toEntity()

        whenever(schemasRepository.save(entity)).thenReturn(entity)
        val result = schemasService.save(model)

        assertEquals(model, result)
        verify(schemasRepository).save(entity)
    }

    @Test
    fun `findById - happy path`() {
        val id = randomId()
        val model = schema(
            id = id,
            items = emptyList(),
        )

        whenever(schemasRepository.findById(id))
            .thenReturn(Optional.of(model.toEntity()))

        val result = schemasService.findById(id)

        assertEquals(model, result)
        verify(schemasRepository).findById(id)
    }

    @Test
    fun `findById - entity not found`() {
        val id = randomId()

        whenever(schemasRepository.findById(id))
            .thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> {
            schemasService.findById(id)
        }

        verify(schemasRepository).findById(id)
    }

    @Test
    fun `findAll - happy path`() {
        val models = listOf(
            schema(items = emptyList()),
            schema(items = emptyList()),
            schema(items = emptyList()),
        )

        whenever(schemasRepository.findAll())
            .thenReturn(models.map { it.toEntity() })

        val result = schemasService.findAll()

        assertEquals(models, result)
        verify(schemasRepository).findAll()
    }

}