package mykyta.titov.categorychallenge.core.providers.repository

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.details.CategoriesRepository
import mykyta.titov.categorychallenge.data.repositories.details.Remote

class CategoriesRepositoryProvider(
        private val categoriesRemoteProvider: Provider<Remote>
) : Provider<CategoriesRepository>() {

    private val categoriesRepository: CategoriesRepository by lazy {
        CategoriesRepository(
                remoteDataSource = categoriesRemoteProvider.get()
        )
    }

    override fun get(): CategoriesRepository = categoriesRepository
}