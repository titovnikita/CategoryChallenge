package mykyta.titov.categorychallenge.data.mappers

import mykyta.titov.categorychallenge.data.dtos.ItemDto
import mykyta.titov.categorychallenge.domain.Item
import mykyta.titov.categorychallenge.utils.extensions.orDefault

class ItemsMapper {

    fun transform(itemDto: ItemDto): Item = with(itemDto) {
        Item(
                id = id.orDefault(),
                imageUrl = urls?.regular.orDefault()
        )
    }
}