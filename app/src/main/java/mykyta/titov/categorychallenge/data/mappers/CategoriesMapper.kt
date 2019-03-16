package mykyta.titov.categorychallenge.data.mappers

import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.utils.extensions.orDefault
import mykyta.titov.tracking.data.entities.CategoryEntity

class CategoriesMapper {

    fun transform(categoryDto: CategoryDto): Category = with(categoryDto) {
        Category(
                id = id.orDefault(),
                imageUrl = coverPhoto?.urls?.regular.orDefault()
        )
    }

    fun transform(categoryEntity: CategoryEntity): Category = with(categoryEntity) {
        Category(
                id = categoryId,
                imageUrl = categoryImageUrl
        )
    }
}