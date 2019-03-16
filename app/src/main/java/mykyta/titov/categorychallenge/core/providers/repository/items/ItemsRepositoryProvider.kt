package mykyta.titov.categorychallenge.core.providers.repository.items

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository

class ItemsRepositoryProvider(
        private val itemsRemoteProvider: Provider<ItemsRepository.Remote>
) : Provider<ItemsRepository>() {

    private val itemsRepository: ItemsRepository by lazy {
        ItemsRepository(
                remoteDataSource = itemsRemoteProvider.get()
        )
    }

    override fun get(): ItemsRepository = itemsRepository
}