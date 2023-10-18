package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.repositories.CategoriesRepository

@Service
class CategoriesService(
    private val categoriesRepository: CategoriesRepository
) {

    fun save(category: Category): Category =
        categoriesRepository.save(category.toEntity()).toModel()
}