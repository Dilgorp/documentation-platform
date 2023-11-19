package ru.dilgorp.documentation.platform.editor.domain.services

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import ru.dilgorp.documentation.platform.domain.models.Category
import ru.dilgorp.documentation.platform.domain.models.Item
import ru.dilgorp.documentation.platform.domain.models.PatchItemCategory
import ru.dilgorp.documentation.platform.domain.models.PatchItemProperty
import ru.dilgorp.documentation.platform.domain.test.data.category
import ru.dilgorp.documentation.platform.domain.test.data.item.item
import ru.dilgorp.documentation.platform.domain.test.data.item.itemList
import ru.dilgorp.documentation.platform.domain.test.data.property
import ru.dilgorp.documentation.platform.domain.test.utils.randomUuid
import ru.dilgorp.documentation.platform.editor.base.BaseServiceTest
import ru.dilgorp.documentation.platform.editor.data.uploadItem

internal class AdminServiceTest : BaseServiceTest() {
    @Autowired
    private lateinit var adminService: AdminService

    @MockBean
    private lateinit var itemsService: ItemsService

    @MockBean
    private lateinit var propertiesService: PropertiesService

    @MockBean
    private lateinit var categoriesService: CategoriesService

    @Test
    fun `uploadData - happy path`() {
        val existingTitle = randomUuid()
        val uploadItem = uploadItem()
        val uploadItems = listOf(
            uploadItem(title = existingTitle),
            uploadItem(title = existingTitle),
            uploadItem,
        )
        val uploadProperty = uploadItem.properties.first()
        val property = property(title = uploadProperty.title)
        val item = item(
            title = uploadItem.title,
            description = uploadItem.description,
        )
        val uploadCategory = uploadItem.categories.first()
        val category = category(title = uploadCategory.title)

        whenever(itemsService.findAllByTitle(existingTitle)).thenReturn(listOf(itemList()))
        whenever(itemsService.findAllByTitle(uploadItem.title)).thenReturn(emptyList())

        whenever(
            itemsService.save(
                Item(
                    title = uploadItem.title,
                    description = uploadItem.description,
                )
            )
        ).thenReturn(item)

        whenever(propertiesService.findByTitle(uploadProperty.title))
            .thenReturn(property)

        whenever(categoriesService.findByTitle(uploadCategory.title)).thenReturn(null)
        whenever(categoriesService.save(Category(title = uploadCategory.title)))
            .thenReturn(category)

        adminService.uploadData(uploadItems)

        verify(itemsService, times(2)).findAllByTitle(existingTitle)
        verify(itemsService).findAllByTitle(uploadItem.title)
        verify(itemsService).save(
            Item(
                title = uploadItem.title,
                description = uploadItem.description,
            )
        )
        verify(propertiesService).findByTitle(uploadProperty.title)
        verify(propertiesService, times(0)).save(any())
        verify(itemsService).createOrUpdateProperty(
            PatchItemProperty(
                itemId = requireNotNull(item.id),
                propertyId = requireNotNull(property.id),
                value = uploadProperty.value,
            )
        )
        verify(categoriesService).findByTitle(uploadCategory.title)
        verify(categoriesService).save(Category(title = uploadCategory.title))
        verify(itemsService).createOrUpdateCategory(
            PatchItemCategory(
                itemId = requireNotNull(item.id),
                categoryId = requireNotNull(category.id),
                value = uploadCategory.name,
            )
        )
    }
}