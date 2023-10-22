package ru.dilgorp.documentation.platform.editor.rest.controllers

import org.springframework.web.bind.annotation.*
import ru.dilgorp.documentation.platform.domain.dto.SchemaDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.editor.domain.services.SchemasService

@RestController
@RequestMapping("/schemas")
class SchemasController(
    private val schemasService: SchemasService
) {

    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id")
        id: Long,
    ): SchemaDto = schemasService.findById(id).toDto()

    @GetMapping
    fun findAll(): List<SchemaDto> = schemasService.findAll().map { it.toDto() }

    @PostMapping
    fun create(
        @RequestBody
        schemaDto: SchemaDto,
    ): SchemaDto = schemasService.save(schemaDto.toModel()).toDto()
}