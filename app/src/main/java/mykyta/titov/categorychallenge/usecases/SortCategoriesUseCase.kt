package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.domain.Category.Weight.*

class SortCategoriesUseCase {

    fun sort(categories: List<Category>): List<Category> {
        if (categories.count() >= RESIZABLE_COUNT) {
            val sortedCategories = categories.sortedByDescending { category -> category.popularity }
            for (i in 0 until RESIZABLE_COUNT) {
                sortedCategories[i].weight = when {
                    categories.count { category -> category.popularity >= sortedCategories[i].popularity } == 1 -> BIG
                    categories.count { category -> category.popularity >= sortedCategories[i].popularity } > 1 -> MEDIUM
                    else -> NORMAL
                }
            }

            return sortedCategories
        }

        return categories
    }
}

private const val RESIZABLE_COUNT = 2