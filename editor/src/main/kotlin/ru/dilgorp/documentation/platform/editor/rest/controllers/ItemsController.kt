package ru.dilgorp.documentation.platform.editor.rest.controllers

import org.springframework.web.bind.annotation.*
import ru.dilgorp.documentation.platform.domain.dto.ItemDto
import ru.dilgorp.documentation.platform.domain.dto.PatchPropertyDto
import ru.dilgorp.documentation.platform.domain.dto.toDto
import ru.dilgorp.documentation.platform.editor.domain.services.ItemsService

@RestController
@RequestMapping("/items")
class ItemsController(
    private val itemsService: ItemsService
) {

    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id")
        id: Long,
    ): ItemDto = itemsService.findById(id).toDto()

    @GetMapping
    fun findAll(): List<ItemDto> = itemsService.findAll().map { it.toDto() }

    @GetMapping("/map")
    fun findAllById(
        @RequestParam("ids")
        ids: List<Long>,
    ): Map<Long, ItemDto> = itemsService
        .findAllByIds(ids).map { it.key to it.value.toDto() }.toMap()

    @PostMapping
    fun create(
        @RequestBody
        itemDto: ItemDto,
    ): ItemDto = itemsService.save(itemDto.toModel()).toDto()

    @PostMapping("/{itemId}/properties")
    fun createProperty(
        @PathVariable("itemId")
        itemId: Long,
        @RequestBody
        patchDto: PatchPropertyDto
    ) {
        itemsService.createOrUpdate(patchDto.toModel(itemId))
    }

    @PatchMapping("/{itemId}/properties/{itemPropertyId}")
    fun updateProperty(
        @PathVariable("itemId")
        itemId: Long,
        @PathVariable("itemPropertyId")
        itemPropertyId: Long,
        @RequestBody
        patchDto: PatchPropertyDto
    ) {
        itemsService.createOrUpdate(patchDto.toModel(itemPropertyId, itemId))
    }
}