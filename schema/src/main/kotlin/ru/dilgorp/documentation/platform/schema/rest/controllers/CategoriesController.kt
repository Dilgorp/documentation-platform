package ru.dilgorp.documentation.platform.schema.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.dilgorp.documentation.platform.domain.dto.CategoryDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.schema.domain.services.CategoriesService

@RestController
@RequestMapping("/categories")
class CategoriesController(
    private val categoriesService: CategoriesService,
) {

    @GetMapping
    fun getCategories(): List<CategoryDto> =
        categoriesService.findCategories().map { it.toDto() }
}