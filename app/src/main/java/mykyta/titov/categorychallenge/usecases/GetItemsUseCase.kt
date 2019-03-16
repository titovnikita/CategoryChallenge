package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.data.mappers.ItemsMapper
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import mykyta.titov.categorychallenge.domain.Item

class GetItemsUseCase(
        private val itemsRepository: ItemsRepository,
        private val itemsMapper: ItemsMapper
) {

    @Throws(IllegalStateException::class)
    fun getItems(id: String): List<Item> {
        val response = itemsRepository.getItems(id).execute()
        return with(response) {
            val items = body()

            when (items) {
                null -> throw IllegalStateException("Unable to retrieve items!")
                else -> items.map { categoryDto ->
                    itemsMapper.transform(categoryDto)
                }
            }
        }
    }
}