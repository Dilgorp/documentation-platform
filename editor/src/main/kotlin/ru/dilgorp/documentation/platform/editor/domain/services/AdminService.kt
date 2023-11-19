package ru.dilgorp.documentation.platform.editor.domain.services

import org.springframework.stereotype.Service
import ru.dilgorp.documentation.platform.domain.models.*
import ru.dilgorp.documentation.platform.editor.rest.dto.UploadItem

@Service
class AdminService(
    private val itemsService: ItemsService,
    private val propertiesService: PropertiesService,
    private val categoriesService: CategoriesService,
) {

    fun uploadData(items: List<UploadItem>) {
        items
            .filter { itemsService.findAllByTitle(it.title).isEmpty() }
            .forEach { uploadItem ->

                val item = itemsService.save(
                    Item(
                        title = uploadItem.title,
                        description = uploadItem.description,
                    )
                )

                uploadItem.properties.forEach { uploadProperty ->
                    val property = propertiesService.findByTitle(uploadProperty.title)
                        ?: propertiesService.save(Property(title = uploadProperty.title))

                    itemsService.createOrUpdateProperty(
                        PatchItemProperty(
                            itemId = requireNotNull(item.id),
                            propertyId = requireNotNull(property.id),
                            value = uploadProperty.value,
                        )
                    )
                }

                uploadItem.categories.forEach { uploadCategory ->
                    val category = categoriesService.findByTitle(uploadCategory.title)
                        ?: categoriesService.save(Category(title = uploadCategory.title))

                    itemsService.createOrUpdateCategory(
                        PatchItemCategory(
                            itemId = requireNotNull(item.id),
                            categoryId = requireNotNull(category.id),
                            value = uploadCategory.name,
                        )
                    )
                }

            }
    }
}