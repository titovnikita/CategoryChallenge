package mykyta.titov.categorychallenge.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import mykyta.titov.categorychallenge.data.dtos.CoverPhotoDto
import mykyta.titov.categorychallenge.data.dtos.UrlsDto
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import mykyta.titov.categorychallenge.utils.MockCall
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class CategoriesRepositoryTest {

    private val mockRemoteDataSource: CategoriesRepository.Remote = mock()

    private val categoriesRepository = CategoriesRepository(mockRemoteDataSource)

    @Test
    fun `Get categories success response from repository with categories inside`() {
        val categoryId = "category_id"
        val imageUrl = "image_url"
        val urlDto = UrlsDto(imageUrl)
        val coverPhotoOne = CoverPhotoDto(urlDto)
        val repositoryCategoriesResponse = listOf(CategoryDto(categoryId, coverPhotoOne), CategoryDto(categoryId, coverPhotoOne))

        givenResult(repositoryCategoriesResponse)

        val response = categoriesRepository.getCategories(anyInt()).execute()
        with(response) {
            assert(isSuccessful)

            val categories = body()!!
            categories.forEach { category ->
                with(category) {
                    assert(id == categoryId)
                    assertNotNull(coverPhoto?.urls?.regular)
                    assert(coverPhoto?.urls?.regular == imageUrl)
                }
            }
        }
    }

    @Test
    fun `Get categories success response from repository with empty categories response`() {
        val repositoryCategoriesResponse = listOf<CategoryDto>()

        givenResult(repositoryCategoriesResponse)

        val response = categoriesRepository.getCategories(anyInt()).execute()
        with(response) {
            assert(isSuccessful)

            val categories = body()!!
            assert(categories.isEmpty())
        }
    }

    @Test
    fun `Get categories error response from repository`() {
        val errorCode = 404
        val errorContentType = "content_type"
        val errorContent = "not_found"

        givenError(errorCode, errorContentType, errorContent)

        val response = categoriesRepository.getCategories(anyInt()).execute()
        with(response) {
            assert(!isSuccessful)

            assert(code() == errorCode)
            assertNull(body())
            assertNotNull(errorBody())
        }
    }

    private fun givenResult(listDTOs: List<CategoryDto>) {
        whenever(mockRemoteDataSource.getCategories(anyInt()))
                .thenReturn(MockCall.buildSuccess(listDTOs))
    }

    private fun givenError(errorCode: Int, contentType: String, content: String) {
        whenever(mockRemoteDataSource.getCategories(anyInt()))
                .thenReturn(MockCall.buildHttpError(errorCode, contentType, content))
    }
}