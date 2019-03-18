package mykyta.titov.categorychallenge.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import mykyta.titov.categorychallenge.data.dtos.ItemDto
import mykyta.titov.categorychallenge.data.dtos.UrlsDto
import mykyta.titov.categorychallenge.data.mappers.ItemsMapper
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import mykyta.titov.categorychallenge.usecases.GetItemsUseCase
import mykyta.titov.categorychallenge.utils.MockCall
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GetItemsUseCaseTest {

    private val mockItemsRepository: ItemsRepository = mock()

    private val itemsMapper = ItemsMapper()
    private val getItemsUseCase = GetItemsUseCase(mockItemsRepository, itemsMapper)

    @Test
    fun `Get items success response from usecase with items inside`() {
        val itemId = "item_id"
        val itemImageUrl = "image_url"
        val urlDto = UrlsDto(itemImageUrl)
        val itemsResponse = listOf(ItemDto(itemId, urlDto), ItemDto(itemId, urlDto))

        givenResult(itemsResponse)

        val items = getItemsUseCase.getItems(anyString())
        items.forEach { item ->
            with(item) {
                assert(id.isNotEmpty())
                assert(id == itemId)
                assert(imageUrl.isNotEmpty())
                assert(imageUrl == itemImageUrl)
            }
        }
    }

    @Test
    fun `Get items success response from usecase with empty items response`() {
        val itemsResponse = listOf<ItemDto>()

        givenResult(itemsResponse)

        val items = getItemsUseCase.getItems(anyString())
        assert(items.isEmpty())
    }

    @Test(expected = IllegalStateException::class)
    fun `Get items error response from usecase`() {
        val errorCode = 401
        val errorContentType = "content_type"
        val errorContent = "no_auth"

        givenError(errorCode, errorContentType, errorContent)

        getItemsUseCase.getItems(anyString())
    }

    private fun givenResult(listDTOs: List<ItemDto>) {
        whenever(mockItemsRepository.getItems(anyString()))
                .thenReturn(MockCall.buildSuccess(listDTOs))
    }

    private fun givenError(errorCode: Int, contentType: String, content: String) {
        whenever(mockItemsRepository.getItems(anyString()))
                .thenReturn(MockCall.buildHttpError(errorCode, contentType, content))
    }
}