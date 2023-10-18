package ru.dilgorp.documentation.platform.editor.domain.converters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.editor.persistence.data.categoryEntity

internal class CategoryConverterTest {

    @Test
    fun `toModel - happy path`() {
        val entity = categoryEntity(
            id = 1,
            title = "Title",
        )

        val model = category(
            id = 1,
            title = "Title",
        )

        assertEquals(model, entity.toModel())
    }

    @Test
    fun `toEntity - happy path`() {
        val entity = categoryEntity(
            id = 1,
            title = "Title",
        )

        val model = category(
            id = 1,
            title = "Title",
        )

        assertEquals(entity, model.toEntity())
    }
}