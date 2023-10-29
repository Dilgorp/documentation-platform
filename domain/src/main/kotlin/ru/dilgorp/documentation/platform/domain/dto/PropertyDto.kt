package ru.dilgorp.documentation.platform.domain.dto

import ru.dilgorp.documentation.platform.domain.models.Property

data class PropertyDto(
    val id: Long? = null,
    val title: String,
) {
    fun toModel(): Property = Property(
        id = id,
        title = title,
    )
}

data class PatchPropertyDto(
    val title: String,
) {
    fun toModel(): Property = Property(
        title = title,
    )

    fun toModel(id: Long): Property = Property(
        id = id,
        title = title,
    )
}

fun Property.toDto(): PropertyDto = PropertyDto(
    id = id,
    title = title,
)
