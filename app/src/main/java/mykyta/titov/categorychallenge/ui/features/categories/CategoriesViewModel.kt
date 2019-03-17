package mykyta.titov.categorychallenge.ui.features.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseViewModel
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import mykyta.titov.categorychallenge.usecases.SortCategoriesUseCase
import mykyta.titov.categorychallenge.usecases.UpdatePopularityUseCase
import java.util.concurrent.Executor


class CategoriesViewModel(
        private val executor: Executor,
        private val getCarDetailsUseCase: GetCategoriesUseCase,
        private val updatePopularityUseCase: UpdatePopularityUseCase,
        private val sortCategoriesUseCase: SortCategoriesUseCase
) : BaseViewModel() {

    private val openItemsScreenEvent = SingleLiveEvent<Category>()

    private val uiModel: MutableLiveData<CategoriesUiModel> = MutableLiveData()

    fun openItemsScreenEvents(): SingleLiveEvent<Category> = openItemsScreenEvent

    fun uiEvents(): LiveData<CategoriesUiModel> = uiModel

    fun start() {
        executor.execute {
            try {
                val categories = sortCategoriesUseCase.sort(getCarDetailsUseCase.getCategories())
                uiModel.postValue(CategoriesUiModel(State.Loaded(categories)))
            } catch (exception: IllegalStateException) {
                uiModel.postValue(CategoriesUiModel(State.Error))
            }
        }
    }

    fun onCategoryClicked(category: Category) {
        updatePopularityUseCase.incrementPopularity(category)

        openItemsScreenEvent.value = category
    }
}