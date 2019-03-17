package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.tracking.data.entities.TrackingEntity

class GetTopCategoryUseCase(
        private val trackingRepository: TrackingRepository,
        private val categoriesMapper: CategoriesMapper
) {

    fun getMostPopularCategory(): Category? {
        val cursor = trackingRepository.queryByPopularity(COUNT_SINGLE)
        return if (cursor != null && cursor.count > 0 && cursor.moveToFirst()) {
            categoriesMapper.transform(TrackingEntity(cursor))
        } else {
            null
        }
    }
}

private const val COUNT_SINGLE = 1