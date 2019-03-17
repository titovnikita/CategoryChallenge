package mykyta.titov.categorychallenge.usecases

import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.domain.Category.Weight.*

class SortCategoriesUseCase {

    fun sort(categories: List<Category>): List<Category> {
        with(categories) {
            if (count() >= RESIZABLE_COUNT) {
                val sortedCategories = sortedByDescending { category -> category.popularity }
                val isAllValuesSame = sortedCategories.distinctBy { category -> category.popularity }.size <= 1

                for (i in 0 until RESIZABLE_COUNT) {
                    sortedCategories[i].weight = when {
                        isAllValuesSame -> NORMAL
                        count { category -> category.popularity > sortedCategories[i].popularity } == NONE_COUNT -> BIG
                        count { category -> category.popularity >= sortedCategories[i].popularity } <= RESIZABLE_COUNT -> MEDIUM
                        else -> NORMAL
                    }
                }

                return sortedCategories
            }
        }

        return categories
    }
}

private const val NONE_COUNT = 0
private const val RESIZABLE_COUNT = 2