package mykyta.titov.categorychallenge.core.providers.repository.categories

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository

class CategoriesRepositoryProvider(
        private val categoriesRemoteProvider: Provider<CategoriesRepository.Remote>
) : Provider<CategoriesRepository>() {

    private val categoriesRepository: CategoriesRepository by lazy {
        CategoriesRepository(
                remoteDataSource = categoriesRemoteProvider.get()
        )
    }

    override fun get(): CategoriesRepository = categoriesRepository
}