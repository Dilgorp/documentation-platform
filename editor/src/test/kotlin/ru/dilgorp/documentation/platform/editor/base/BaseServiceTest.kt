package ru.dilgorp.documentation.platform.editor.base

import org.springframework.boot.test.mock.mockito.MockBean
import ru.dilgorp.documentation.platform.editor.persistence.repositories.CategoriesRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.PropertiesRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsPropertiesRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.item.ItemsRepository
import ru.dilgorp.documentation.platform.editor.persistence.repositories.schema.SchemasRepository

abstract class BaseServiceTest : BaseTest() {
    @MockBean
    protected lateinit var categoriesRepository: CategoriesRepository

    @MockBean
    protected lateinit var propertiesRepository: PropertiesRepository

    @MockBean
    protected lateinit var itemsRepository: ItemsRepository

    @MockBean
    protected lateinit var itemsPropertiesRepository: ItemsPropertiesRepository

    @MockBean
    protected lateinit var schemasRepository: SchemasRepository
}