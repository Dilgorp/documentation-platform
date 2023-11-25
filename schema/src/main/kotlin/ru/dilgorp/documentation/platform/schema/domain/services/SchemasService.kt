package ru.dilgorp.documentation.platform.schema.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.dilgorp.documentation.platform.domain.models.FullSchema
import ru.dilgorp.documentation.platform.schema.domain.converters.toModel
import ru.dilgorp.documentation.platform.schema.persistence.repositories.SchemasRepository
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional(readOnly = true)
class SchemasService(
    private val schemasRepository: SchemasRepository
) {

    fun findSchemas(): List<FullSchema> =
        schemasRepository.findAll().map { it.toModel() }

    fun findSchemaById(schemaId: Long): FullSchema? =
        schemasRepository.findById(schemaId).map { it.toModel() }.getOrNull()
}