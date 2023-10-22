package ru.dilgorp.documentation.platform.editor.rest.controllers

import org.springframework.web.bind.annotation.*
import ru.dilgorp.documentation.platform.domain.dto.PropertyDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.editor.domain.services.PropertiesService

@RestController
@RequestMapping("/properties")
class PropertiesController(
    private val propertiesService: PropertiesService
) {

    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id")
        id: Long,
    ): PropertyDto = propertiesService.findById(id).toDto()

    @GetMapping
    fun findAll(): List<PropertyDto> = propertiesService.findAll().map { it.toDto() }

    @GetMapping("/map")
    fun findAllById(
        @RequestParam("ids")
        ids: List<Long>,
    ): Map<Long, PropertyDto> = propertiesService
        .findAllByIds(ids).map { it.key to it.value.toDto() }.toMap()

    @PostMapping
    fun create(
        @RequestBody
        propertyDto: PropertyDto,
    ): PropertyDto = propertiesService.save(propertyDto.toModel()).toDto()
}