package ru.dilgorp.documentation.platform.editor.rest.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ru.dilgorp.documentation.platform.editor.domain.services.AdminService
import ru.dilgorp.documentation.platform.editor.rest.dto.UploadItem

@RestController
@RequestMapping("/admin")
class AdminController(
    private val objectMapper: ObjectMapper,
    private val adminService: AdminService,
) {

    @PostMapping("/data")
    fun uploadData(
        @RequestParam("file")
        file: MultipartFile
    ) {
        val items = objectMapper.readValue(file.bytes, object : TypeReference<List<UploadItem>>() {})
        adminService.uploadData(items)
    }
}