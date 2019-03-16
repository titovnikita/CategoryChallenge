package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.details.CategoriesRepository
import mykyta.titov.categorychallenge.domain.Category

class GetCategoriesUseCase(
        private val categoriesRepository: CategoriesRepository,
        private val categoriesMapper: CategoriesMapper
) {

    @Throws(IllegalStateException::class)
    fun getCategories(): List<Category> {
        val response = categoriesRepository.getCategories().execute()
        return with(response) {
            val categories = body()

            when (categories) {
                null -> throw IllegalStateException("Unable to retrieve items!")
                else -> categories.map { categoryDto ->
                    categoriesMapper.transform(categoryDto)
                }
            }
        }
    }
}