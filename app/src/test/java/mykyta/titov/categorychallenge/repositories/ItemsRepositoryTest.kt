package mykyta.titov.categorychallenge.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import mykyta.titov.categorychallenge.data.dtos.ItemDto
import mykyta.titov.categorychallenge.data.dtos.UrlsDto
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import mykyta.titov.categorychallenge.utils.MockCall
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class ItemsRepositoryTest {

    private val mockRemoteDataSource: ItemsRepository.Remote = mock()

    private val itemsRepository = ItemsRepository(mockRemoteDataSource)

    @Test
    fun `Get items success response from repository with items inside`() {
        val itemId = "item_id"
        val imageUrl = "image_url"
        val urlDto = UrlsDto(imageUrl)
        val repositoryItemsResponse = listOf(ItemDto(itemId, urlDto), ItemDto(itemId, urlDto))

        givenResult(repositoryItemsResponse)

        val response = itemsRepository.getItems(anyString()).execute()
        with(response) {
            assert(isSuccessful)

            val items = body()!!
            items.forEach { item ->
                with(item) {
                    assert(id == itemId)
                    assertNotNull(urls?.regular)
                    assert(urls?.regular == imageUrl)
                }
            }
        }
    }

    @Test
    fun `Get items success response from repository with empty items response`() {
        val repositoryItemsResponse = listOf<ItemDto>()

        givenResult(repositoryItemsResponse)

        val response = itemsRepository.getItems(anyString()).execute()
        with(response) {
            assert(isSuccessful)

            val items = body()!!
            assert(items.isEmpty())
        }
    }

    @Test
    fun `Get items error response from repository`() {
        val errorCode = 401
        val errorContentType = "content_type"
        val errorContent = "no_auth"

        givenError(errorCode, errorContentType, errorContent)

        val response = itemsRepository.getItems(anyString()).execute()
        with(response) {
            assert(!isSuccessful)

            assert(code() == errorCode)
            assertNull(body())
            assertNotNull(errorBody())
        }
    }

    private fun givenResult(listDTOs: List<ItemDto>) {
        whenever(mockRemoteDataSource.getItems(anyString()))
                .thenReturn(MockCall.buildSuccess(listDTOs))
    }

    private fun givenError(errorCode: Int, contentType: String, content: String) {
        whenever(mockRemoteDataSource.getItems(anyString()))
                .thenReturn(MockCall.buildHttpError(errorCode, contentType, content))
    }
}