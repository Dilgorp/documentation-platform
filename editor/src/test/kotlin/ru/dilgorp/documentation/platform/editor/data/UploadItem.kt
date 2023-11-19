package ru.dilgorp.documentation.platform.editor.data

import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.editor.rest.dto.UploadItem
import ru.dilgorp.documentation.platform.editor.rest.dto.UploadItemCategory
import ru.dilgorp.documentation.platform.editor.rest.dto.UploadItemProperty

fun uploadItemCategory(
    title: String = randomUuid(),
    name: String = randomUuid(),
): UploadItemCategory = UploadItemCategory(
    title = title,
    name = name,
)

fun uploadItemProperty(
    title: String = randomUuid(),
    value: String = randomUuid(),
): UploadItemProperty = UploadItemProperty(
    title = title,
    value = value,
)

fun uploadItem(
    title: String = randomUuid(),
    description: String? = randomUuid(),
    categories: List<UploadItemCategory> = listOf(uploadItemCategory()),
    properties: List<UploadItemProperty> = listOf(uploadItemProperty()),
): UploadItem = UploadItem(
    title = title,
    description = description,
    categories = categories,
    properties = properties,
)