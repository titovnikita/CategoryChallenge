package mykyta.titov.categorychallenge.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mykyta.titov.categorychallenge.ui.features.categories.CategoriesViewModel
import mykyta.titov.categorychallenge.ui.features.highligts.HighlightsViewModel
import mykyta.titov.categorychallenge.ui.features.home.HomeViewModel
import mykyta.titov.categorychallenge.ui.features.image.ImageViewModel
import mykyta.titov.categorychallenge.ui.features.items.ItemsViewModel
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import mykyta.titov.categorychallenge.usecases.GetItemsUseCase
import mykyta.titov.categorychallenge.usecases.GetTopCategoryUseCase
import mykyta.titov.categorychallenge.usecases.UpdatePopularityUseCase
import java.util.concurrent.Executor


class ViewModelFactory(
        private val executor: Executor,
        private val getCategoriesUseCase: GetCategoriesUseCase,
        private val getItemsUseCase: GetItemsUseCase,
        private val updatePopularityUseCase: UpdatePopularityUseCase,
        private val getTopCategoryUseCase: GetTopCategoryUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            with(modelClass) {
                return when {
                    isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(executor, getTopCategoryUseCase) as T
                    isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(executor, getCategoriesUseCase, updatePopularityUseCase) as T
                    isAssignableFrom(HighlightsViewModel::class.java) -> HighlightsViewModel() as T
                    isAssignableFrom(ImageViewModel::class.java) -> ImageViewModel() as T
                    isAssignableFrom(ItemsViewModel::class.java) -> ItemsViewModel(getItemsUseCase, executor) as T
                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
}