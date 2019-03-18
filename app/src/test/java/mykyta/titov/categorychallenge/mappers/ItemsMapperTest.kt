package mykyta.titov.categorychallenge.mappers

import mykyta.titov.categorychallenge.data.dtos.ItemDto
import mykyta.titov.categorychallenge.data.dtos.UrlsDto
import mykyta.titov.categorychallenge.data.mappers.ItemsMapper
import mykyta.titov.categorychallenge.domain.Item
import org.junit.Test

class ItemsMapperTest {

    private val itemsMapper = ItemsMapper()

    @Test
    fun `Map item dto to item with nulls`() {
        val itemDto = ItemDto(null, null)

        val itemMapped: Item = itemsMapper.transform(itemDto)
        with(itemMapped) {
            assert(id.isEmpty())
            assert(imageUrl.isEmpty())
        }
    }

    @Test
    fun `Map item dto to item with data`() {
        val itemId = "id"
        val regularImageUrl = "regular"

        val urlsDto = UrlsDto(regularImageUrl)
        val itemDto = ItemDto(itemId, urlsDto)

        val itemMapped: Item = itemsMapper.transform(itemDto)
        with(itemMapped) {
            assert(id.isNotEmpty())
            assert(id == itemId)
            assert(imageUrl.isNotEmpty())
            assert(imageUrl == regularImageUrl)
        }
    }
}