package ru.dilgorp.documentation.platform.schema.persistence.repositories

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.schema.persistence.entities.CategoryEntity

interface CategoriesRepository : CrudRepository<CategoryEntity, Long>