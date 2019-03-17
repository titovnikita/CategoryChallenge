package mykyta.titov.categorychallenge.ui.features.highligts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.ui.base.BaseViewModel
import mykyta.titov.categorychallenge.usecases.GetTopCategoryUseCase
import java.util.concurrent.Executor


class HighlightsViewModel(
        private val executor: Executor,
        private val getTopCategoryUseCase: GetTopCategoryUseCase
) : BaseViewModel() {

    private val openCategoriesScreenEvent = SingleLiveEvent<Unit>()

    fun openCategoriesScreenEvents(): SingleLiveEvent<Unit> = openCategoriesScreenEvent

    private val uiModel: MutableLiveData<HighlightsUiModel> = MutableLiveData()

    fun uiEvents(): LiveData<HighlightsUiModel> = uiModel

    fun start() {
        executor.execute {
            val category = getTopCategoryUseCase.getMostPopularCategory()
            uiModel.postValue(
                    HighlightsUiModel(
                            category?.imageUrl
                                    ?: throw IllegalStateException("You should have most popular category when open this fragment!")
                    )
            )
        }
    }

    fun onCategoriesClicked() {
        openCategoriesScreenEvent.call()
    }
}