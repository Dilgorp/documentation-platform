package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import ru.dilgorp.documentation.platform.domain.models.Property
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.repositories.PropertiesRepository

@Service
class PropertiesService(
    private val propertiesRepository: PropertiesRepository
) {

    fun save(property: Property): Property =
        propertiesRepository.save(property.toEntity()).toModel()

    fun findById(propertyId: Long): Property =
        propertiesRepository.findById(propertyId).get().toModel()

    fun findAllByIds(propertiesIds: List<Long>): Map<Long, Property> =
        propertiesRepository.findAllById(propertiesIds)
            .associate { requireNotNull(it.id) to it.toModel() }

    fun findAll(): List<Property> =
        propertiesRepository.findAll().map { it.toModel() }

    fun findByTitle(title: String): Property? =
        propertiesRepository.findByTitle(title)?.toModel()

}