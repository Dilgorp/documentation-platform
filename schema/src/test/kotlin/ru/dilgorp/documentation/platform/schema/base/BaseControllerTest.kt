package ru.dilgorp.documentation.platform.schema.base

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import ru.dilgorp.documentation.platform.schema.domain.services.SchemasService

class BaseControllerTest : BaseTest() {
    @Autowired
    protected lateinit var mvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @MockBean
    protected lateinit var schemasService: SchemasService
}