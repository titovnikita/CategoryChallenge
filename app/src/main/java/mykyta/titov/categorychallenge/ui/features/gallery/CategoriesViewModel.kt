package mykyta.titov.categorychallenge.ui.features.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseViewModel
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import java.util.concurrent.Executor


class CategoriesViewModel(
        private val getCarDetailsUseCase: GetCategoriesUseCase,
        private val executor: Executor
) : BaseViewModel() {

    private val openImageScreenEvent = SingleLiveEvent<String>()

    private val uiModel: MutableLiveData<CategoryUiModel> = MutableLiveData()

    fun openImageScreenEvents(): SingleLiveEvent<String> = openImageScreenEvent

    fun uiEvents(): LiveData<CategoryUiModel> = uiModel

    fun start() {
        executor.execute {
            try {
                val categories = getCarDetailsUseCase.getCategories()
                uiModel.postValue(CategoryUiModel(State.Loaded(categories)))
            } catch (exception: IllegalStateException) {
                uiModel.postValue(CategoryUiModel(State.Error))
            }
        }
    }

    fun onCategoryClicked(category: Category) {
        openImageScreenEvent.value = category.cover.full
    }
}