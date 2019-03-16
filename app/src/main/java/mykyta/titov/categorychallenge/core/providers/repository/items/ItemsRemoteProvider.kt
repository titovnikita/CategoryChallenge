package mykyta.titov.categorychallenge.core.providers.repository.items

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import retrofit2.Retrofit

class ItemsRemoteProvider(
        private val retrofitProvider: Provider<Retrofit>
) : Provider<ItemsRepository.Remote>() {

    private val remote: ItemsRepository.Remote by lazy {
        ItemsRepository.Remote(
                retrofit = retrofitProvider.get()
        )
    }

    override fun get(): ItemsRepository.Remote = remote
}