package mykyta.titov.categorychallenge.core.providers.repository

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.details.Remote
import retrofit2.Retrofit

class CategoriesRemoteProvider(
        private val retrofitProvider: Provider<Retrofit>
) : Provider<Remote>() {

    private val remote: Remote by lazy {
        Remote(
                retrofit = retrofitProvider.get()
        )
    }

    override fun get(): Remote = remote
}