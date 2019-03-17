package mykyta.titov.categorychallenge.core.providers.usecases

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.usecases.GetTopCategoryUseCase

class GetTopCategoryUseCaseProvider(
        private val trackingRepositoryProvider: Provider<TrackingRepository>,
        private val categoriesMapperProvider: Provider<CategoriesMapper>
) : Provider<GetTopCategoryUseCase>() {

    private val getTopCategoryUseCase: GetTopCategoryUseCase by lazy {
        GetTopCategoryUseCase(
                trackingRepository = trackingRepositoryProvider.get(),
                categoriesMapper = categoriesMapperProvider.get()
        )
    }

    override fun get(): GetTopCategoryUseCase = getTopCategoryUseCase
}