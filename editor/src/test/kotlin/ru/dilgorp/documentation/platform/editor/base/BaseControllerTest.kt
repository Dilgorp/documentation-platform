package ru.dilgorp.documentation.platform.editor.base

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import ru.dilgorp.documentation.platform.editor.domain.services.CategoriesService
import ru.dilgorp.documentation.platform.editor.domain.services.ItemsService
import ru.dilgorp.documentation.platform.editor.domain.services.PropertiesService
import ru.dilgorp.documentation.platform.editor.domain.services.SchemasService

class BaseControllerTest : BaseTest() {
    @Autowired
    protected lateinit var mvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @MockBean
    protected lateinit var propertiesService: PropertiesService

    @MockBean
    protected lateinit var categoriesService: CategoriesService

    @MockBean
    protected lateinit var itemsService: ItemsService

    @MockBean
    protected lateinit var schemasService: SchemasService
}