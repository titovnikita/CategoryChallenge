package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.domain.Category

class UpdatePopularityUseCase(
        private val trackingRepository: TrackingRepository,
        private val categoriesMapper: CategoriesMapper
) {

    fun incrementPopularity(category: Category) {
        trackingRepository.incrementPopularity(categoriesMapper.transform(category))
    }
}