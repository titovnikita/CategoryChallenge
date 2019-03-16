package mykyta.titov.categorychallenge.core.providers.mappers

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.mappers.ItemsMapper

class ItemsMapperProvider : Provider<ItemsMapper>() {

    private val itemsMapper: ItemsMapper by lazy { ItemsMapper() }

    override fun get(): ItemsMapper = itemsMapper
}