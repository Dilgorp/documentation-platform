package ru.dilgorp.documentation.platform.editor.rest.controllers

import org.springframework.web.bind.annotation.*
import ru.dilgorp.documentation.platform.domain.dto.*
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
    fun findAll(): List<SchemaListDto> = schemasService.findAll().map { it.toListDto() }

    @PostMapping
    fun create(
        @RequestBody
        schemaDto: PatchSchemaDto,
    ): SchemaDto = schemasService.save(schemaDto.toModel()).toDto()

    @PatchMapping("/{id}")
    fun update(
        @PathVariable("id")
        id: Long,
        @RequestBody
        schemaDto: PatchSchemaDto,
    ): SchemaDto = schemasService.save(schemaDto.toModel(id)).toDto()

    @PostMapping("/{schemaId}/items")
    fun createItem(
        @PathVariable("schemaId")
        schemaId: Long,
        @RequestBody
        patchDto: PatchSchemaItemDto
    ) {
        schemasService.createOrUpdateItem(patchDto.toModel(schemaId))
    }

    @PatchMapping("/{schemaId}/items/{schemaItemId}")
    fun updateItem(
        @PathVariable("schemaId")
        schemaId: Long,
        @PathVariable("schemaItemId")
        schemaItemId: Long,
        @RequestBody
        patchDto: PatchSchemaItemDto
    ) {
        schemasService.createOrUpdateItem(patchDto.toModel(schemaItemId, schemaId))
    }
}