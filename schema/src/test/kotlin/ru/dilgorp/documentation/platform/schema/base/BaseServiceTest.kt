package ru.dilgorp.documentation.platform.schema.base

import org.springframework.boot.test.mock.mockito.MockBean
import ru.dilgorp.documentation.platform.schema.persistence.repositories.SchemasRepository

abstract class BaseServiceTest : BaseTest() {

    @MockBean
    protected lateinit var schemasRepository: SchemasRepository
}