package mykyta.titov.categorychallenge.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mykyta.titov.categorychallenge.ui.features.gallery.CategoriesViewModel
import mykyta.titov.categorychallenge.ui.features.image.ImageViewModel
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import java.util.concurrent.Executor


class ViewModelFactory(
        private val executor: Executor,
        private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            with(modelClass) {
                return when {
                    isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(getCategoriesUseCase, executor) as T
                    isAssignableFrom(ImageViewModel::class.java) -> ImageViewModel() as T
                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
}