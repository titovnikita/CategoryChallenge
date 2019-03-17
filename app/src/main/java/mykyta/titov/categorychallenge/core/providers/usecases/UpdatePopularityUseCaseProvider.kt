package mykyta.titov.categorychallenge.core.providers.usecases

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.usecases.UpdatePopularityUseCase

class UpdatePopularityUseCaseProvider(
        private val trackingRepositoryProvider: Provider<TrackingRepository>,
        private val categoriesMapperProvider: Provider<CategoriesMapper>
) : Provider<UpdatePopularityUseCase>() {

    private val updatePopularityUseCase: UpdatePopularityUseCase by lazy {
        UpdatePopularityUseCase(
                trackingRepository = trackingRepositoryProvider.get(),
                categoriesMapper = categoriesMapperProvider.get()
        )
    }

    override fun get(): UpdatePopularityUseCase = updatePopularityUseCase
}