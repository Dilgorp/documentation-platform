package ru.dilgorp.documentation.platform.schema.domain.services

import org.springframework.stereotype.Service
import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.schema.domain.converters.toModel
import ru.dilgorp.documentation.platform.schema.persistence.repositories.CategoriesRepository

@Service
class CategoriesService(
    private val categoriesRepository: CategoriesRepository,
) {
    fun findCategories(): List<Category> =
        categoriesRepository.findAll().map { it.toModel() }
}