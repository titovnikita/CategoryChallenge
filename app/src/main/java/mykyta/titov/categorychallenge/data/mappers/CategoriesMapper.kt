package mykyta.titov.categorychallenge.data.mappers

import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.domain.Image
import mykyta.titov.categorychallenge.utils.extensions.orDefault

class CategoriesMapper {

    fun transform(categoryDto: CategoryDto): Category = with(categoryDto) {
        Category(
                id = id.orDefault(),
                cover = with(coverPhoto) {
                    Image(
                            full = urls?.full.orDefault(),
                            regular = urls?.regular.orDefault()
                    )
                }
        )
    }
}