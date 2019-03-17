package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.tracking.data.entities.TrackingEntity

class GetCategoriesUseCase(
        private val categoriesRepository: CategoriesRepository,
        private val trackingRepository: TrackingRepository,
        private val categoriesMapper: CategoriesMapper
) {

    @Throws(IllegalStateException::class)
    fun getCategories(): List<Category> {
        val response = categoriesRepository.getCategories(ITEMS_COUNT).execute()
        return with(response) {
            val categories = body()

            when (categories) {
                null -> throw IllegalStateException("Unable to retrieve items!")
                else -> {
                    val popularCategories: List<Category> = getPopularCategories()

                    categories.map { categoryDto ->
                        categoriesMapper.transform(categoryDto)
                                .apply {
                                    val popularCategory = popularCategories.find { category ->
                                        id == category.id
                                    }

                                    if (popularCategory != null) {
                                        popularity = popularCategory.popularity
                                    }
                                }
                    }
                }
            }
        }
    }

    private fun getPopularCategories(): List<Category> {
        return trackingRepository.queryByPopularity(BY_POPULARITY_COUNT)
                ?.run {
                    mutableListOf<Category>()
                            .apply {
                                if (count > 0) {
                                    while (moveToNext()) {
                                        add(
                                                categoriesMapper.transform(
                                                        TrackingEntity(this@run)
                                                )
                                        )
                                    }
                                }
                            }
                } ?: emptyList()
    }
}

private const val ITEMS_COUNT = 6
private const val BY_POPULARITY_COUNT = 2