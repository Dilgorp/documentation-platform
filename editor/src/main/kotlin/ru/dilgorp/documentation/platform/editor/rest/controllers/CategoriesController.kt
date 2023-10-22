package ru.dilgorp.documentation.platform.editor.rest.controllers

import org.springframework.web.bind.annotation.*
import ru.dilgorp.documentation.platform.domain.dto.CategoryDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.editor.domain.services.CategoriesService

@RestController
@RequestMapping("/categories")
class CategoriesController(
    private val categoriesService: CategoriesService
) {

    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id")
        id: Long,
    ): CategoryDto = categoriesService.findById(id).toDto()

    @GetMapping
    fun findAll(): List<CategoryDto> = categoriesService.findAll().map { it.toDto() }

    @GetMapping("/map")
    fun findAllById(
        @RequestParam("ids")
        ids: List<Long>,
    ): Map<Long, CategoryDto> = categoriesService
        .findAllByIds(ids).map { it.key to it.value.toDto() }.toMap()

    @PostMapping
    fun create(
        @RequestBody
        categoryDto: CategoryDto,
    ): CategoryDto = categoriesService.save(categoryDto.toModel()).toDto()
}