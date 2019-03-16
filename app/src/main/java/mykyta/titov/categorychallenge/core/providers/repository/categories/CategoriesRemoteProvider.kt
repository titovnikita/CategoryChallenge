package mykyta.titov.categorychallenge.core.providers.repository.categories

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import retrofit2.Retrofit

class CategoriesRemoteProvider(
        private val retrofitProvider: Provider<Retrofit>
) : Provider<CategoriesRepository.Remote>() {

    private val remote: CategoriesRepository.Remote by lazy {
        CategoriesRepository.Remote(
                retrofit = retrofitProvider.get()
        )
    }

    override fun get(): CategoriesRepository.Remote = remote
}