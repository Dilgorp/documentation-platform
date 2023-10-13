package ru.dilgorp.documentation.platform.editor.persistence.repositories

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.CategoryEntity

interface CategoriesRepository : CrudRepository<CategoryEntity, Long>