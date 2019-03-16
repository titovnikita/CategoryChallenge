package mykyta.titov.categorychallenge.core.providers.usecases

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.mappers.ItemsMapper
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import mykyta.titov.categorychallenge.usecases.GetItemsUseCase

class GetItemsUseCaseProvider(
        private val itemsRepositoryProvider: Provider<ItemsRepository>,
        private val itemsMapperProvider: Provider<ItemsMapper>
) : Provider<GetItemsUseCase>() {

    private val getItemsUseCase: GetItemsUseCase by lazy {
        GetItemsUseCase(
                itemsRepository = itemsRepositoryProvider.get(),
                itemsMapper = itemsMapperProvider.get()
        )
    }

    override fun get(): GetItemsUseCase = getItemsUseCase
}