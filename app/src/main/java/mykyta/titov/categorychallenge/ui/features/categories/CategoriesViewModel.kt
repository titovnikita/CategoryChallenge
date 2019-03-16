package mykyta.titov.categorychallenge.ui.features.categories

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

    private val openItemsScreenEvent = SingleLiveEvent<Category>()

    private val uiModel: MutableLiveData<CategoriesUiModel> = MutableLiveData()

    fun openItemsScreenEvents(): SingleLiveEvent<Category> = openItemsScreenEvent

    fun uiEvents(): LiveData<CategoriesUiModel> = uiModel

    fun start() {
        executor.execute {
            try {
                val categories = getCarDetailsUseCase.getCategories()
                uiModel.postValue(CategoriesUiModel(State.Loaded(categories)))
            } catch (exception: IllegalStateException) {
                uiModel.postValue(CategoriesUiModel(State.Error))
            }
        }
    }

    fun onCategoryClicked(category: Category) {
        openItemsScreenEvent.value = category
    }
}