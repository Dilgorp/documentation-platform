package ru.dilgorp.documentation.platform.editor.domain.converters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.editor.persistence.data.propertyEntity

internal class PropertyConverterTest {

    @Test
    fun `toModel - happy path`() {
        val entity = propertyEntity(
            id = 1,
            title = "Title",
        )

        val model = property(
            id = 1,
            title = "Title",
        )

        assertEquals(model, entity.toModel())
    }

    @Test
    fun `toEntity - happy path`() {
        val entity = propertyEntity(
            id = 1,
            title = "Title",
        )

        val model = property(
            id = 1,
            title = "Title",
        )

        assertEquals(entity, model.toEntity())
    }
}