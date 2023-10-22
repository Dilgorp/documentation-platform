package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import ru.dilgorp.documentation.platform.domain.models.Schema
import ru.dilgorp.documentation.platform.editor.domain.converters.toEntity
import ru.dilgorp.documentation.platform.editor.domain.converters.toModel
import ru.dilgorp.documentation.platform.editor.persistence.repositories.schema.SchemasRepository

@Service
class SchemasService(
    private val schemasRepository: SchemasRepository
) {

    fun save(schema: Schema): Schema =
        schemasRepository.save(schema.toEntity()).toModel()

    fun findById(schemaId: Long): Schema =
        schemasRepository.findById(schemaId).get().toModel()

    fun findAll(): List<Schema> =
        schemasRepository.findAll().map { it.toModel() }
}