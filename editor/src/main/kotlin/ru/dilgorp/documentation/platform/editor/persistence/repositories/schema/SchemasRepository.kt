package ru.dilgorp.documentation.platform.editor.persistence.repositories.schema

import org.springframework.data.repository.CrudRepository
import ru.dilgorp.documentation.platform.editor.persistence.entities.schema.SchemaEntity

interface SchemasRepository : CrudRepository<SchemaEntity, Long>