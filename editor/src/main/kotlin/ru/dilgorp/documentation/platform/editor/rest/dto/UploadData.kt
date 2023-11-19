package ru.dilgorp.documentation.platform.editor.rest.dto

data class UploadItemCategory(
    val title: String,
    val name: String,
)

data class UploadItemProperty(
    val title: String,
    val value: String,
)

data class UploadItem(
    val title: String,
    val description: String? = null,
    val categories: List<UploadItemCategory>,
    val properties: List<UploadItemProperty>,
)