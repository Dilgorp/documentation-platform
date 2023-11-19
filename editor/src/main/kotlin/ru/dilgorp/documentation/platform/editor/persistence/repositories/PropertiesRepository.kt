package ru.dilgorp.documentation.platform.editor.persistence.repositories

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.PropertyEntity

interface PropertiesRepository : CrudRepository<PropertyEntity, Long> {
    fun findByTitle(title: String): PropertyEntity?
}