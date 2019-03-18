package mykyta.titov.categorychallenge.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import mykyta.titov.categorychallenge.data.dtos.CoverPhotoDto
import mykyta.titov.categorychallenge.data.dtos.UrlsDto
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import mykyta.titov.categorychallenge.utils.MockCall
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class GetCategoriesUseCaseTest {

    private val mockLocalDataSource: TrackingRepository.Local = mock()

    private val trackingRepository = TrackingRepository(mockLocalDataSource)

    private val mockCategoriesRepository: CategoriesRepository = mock()

    private val categoriesMapper = CategoriesMapper()

    private val getCategoriesUseCase = GetCategoriesUseCase(mockCategoriesRepository, trackingRepository, categoriesMapper)

    @Test
    fun `Get categories success response from usecase with categories inside`() {
        val categoryId = "category_id"
        val categoryImageUrl = "image_url"
        val urlDto = UrlsDto(categoryImageUrl)
        val coverPhotoOne = CoverPhotoDto(urlDto)
        val categoriesResponse = listOf(CategoryDto(categoryId, coverPhotoOne), CategoryDto(categoryId, coverPhotoOne))

        givenResult(categoriesResponse)

        val categories = getCategoriesUseCase.getCategories()
        categories.forEach { category ->
            with(category) {
                assert(id.isNotEmpty())
                assert(id == categoryId)
                assert(imageUrl.isNotEmpty())
                assert(imageUrl == categoryImageUrl)
            }
        }
    }

    @Test
    fun `Get categories success response from usecase with empty categories response`() {
        val categoriesResponse = listOf<CategoryDto>()

        givenResult(categoriesResponse)

        val categories = getCategoriesUseCase.getCategories()
        assert(categories.isEmpty())
    }

    @Test(expected = IllegalStateException::class)
    fun `Get categories error response from usecase`() {
        val errorCode = 404
        val errorContentType = "content_type"
        val errorContent = "not_found"

        givenError(errorCode, errorContentType, errorContent)

        getCategoriesUseCase.getCategories()
    }

    private fun givenResult(listDTOs: List<CategoryDto>) {
        whenever(mockCategoriesRepository.getCategories(anyInt()))
                .thenReturn(MockCall.buildSuccess(listDTOs))
    }

    private fun givenError(errorCode: Int, contentType: String, content: String) {
        whenever(mockCategoriesRepository.getCategories(anyInt()))
                .thenReturn(MockCall.buildHttpError(errorCode, contentType, content))
    }
}