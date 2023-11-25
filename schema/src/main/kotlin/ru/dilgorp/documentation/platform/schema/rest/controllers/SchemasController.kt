package ru.dilgorp.documentation.platform.schema.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.dilgorp.documentation.platform.domain.dto.FullSchemaDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.schema.domain.services.SchemasService

@RestController
@RequestMapping("/schemas")
class SchemasController(
    private val schemasService: SchemasService,
) {

    @GetMapping
    fun getSchemas(): List<FullSchemaDto> =
        schemasService.findSchemas().map { it.toDto() }

    @GetMapping("/{id}")
    fun getSchema(
        @PathVariable("id")
        schemaId: Long,
    ): FullSchemaDto? =
        schemasService.findSchemaById(schemaId)?.toDto()
}