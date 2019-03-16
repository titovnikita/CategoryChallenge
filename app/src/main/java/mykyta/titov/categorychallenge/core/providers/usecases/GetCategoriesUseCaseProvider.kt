package mykyta.titov.categorychallenge.core.providers.usecases

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase

class GetCategoriesUseCaseProvider(
        private val categoriesRepositoryProvider: Provider<CategoriesRepository>,
        private val categoriesMapperProvider: Provider<CategoriesMapper>
) : Provider<GetCategoriesUseCase>() {

    private val getCategoriesUseCase: GetCategoriesUseCase by lazy {
        GetCategoriesUseCase(
                categoriesRepository = categoriesRepositoryProvider.get(),
                categoriesMapper = categoriesMapperProvider.get()
        )
    }

    override fun get(): GetCategoriesUseCase = getCategoriesUseCase
}