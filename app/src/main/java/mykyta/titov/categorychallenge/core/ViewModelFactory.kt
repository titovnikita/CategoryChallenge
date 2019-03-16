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
import java.util.concurrent.Executor


class ViewModelFactory(
        private val executor: Executor,
        private val getCategoriesUseCase: GetCategoriesUseCase,
        private val getItemsUseCase: GetItemsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            with(modelClass) {
                return when {
                    isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel() as T
                    isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(getCategoriesUseCase, executor) as T
                    isAssignableFrom(HighlightsViewModel::class.java) -> HighlightsViewModel() as T
                    isAssignableFrom(ImageViewModel::class.java) -> ImageViewModel() as T
                    isAssignableFrom(ItemsViewModel::class.java) -> ItemsViewModel(getItemsUseCase, executor) as T
                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
}