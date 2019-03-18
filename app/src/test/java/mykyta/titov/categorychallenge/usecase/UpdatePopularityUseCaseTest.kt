package mykyta.titov.categorychallenge.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.usecases.UpdatePopularityUseCase
import org.junit.Test

class UpdatePopularityUseCaseTest {

    private val mockLocalDataSource: TrackingRepository.Local = mock()

    private val trackingRepository = TrackingRepository(mockLocalDataSource)

    private val categoriesMapper = CategoriesMapper()

    private val updatePopularityUseCase = UpdatePopularityUseCase(trackingRepository, categoriesMapper)

    @Test
    fun `Update popularity from usecase calls repository local data source once`() {
        val category = Category("category_id", "image_url")
        updatePopularityUseCase.incrementPopularity(category)
        verify(mockLocalDataSource, times(1)).incrementPopularity(any())
    }
}