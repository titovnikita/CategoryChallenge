package mykyta.titov.categorychallenge.ui.features.home

import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseViewModel
import mykyta.titov.categorychallenge.usecases.GetTopCategoryUseCase
import java.util.concurrent.Executor


class HomeViewModel(
        private val executor: Executor,
        private val getTopCategoryUseCase: GetTopCategoryUseCase
) : BaseViewModel() {

    private val openHighlightsScreenEvent = SingleLiveEvent<Unit>()

    private val openCategoriesScreenEvent = SingleLiveEvent<Unit>()

    fun openHighlightsScreenEvents(): SingleLiveEvent<Unit> = openHighlightsScreenEvent

    fun openCategoriesScreenEvents(): SingleLiveEvent<Unit> = openCategoriesScreenEvent

    fun start() {
        executor.execute {
            val category = getTopCategoryUseCase.getMostPopularCategory()
            if (category == null) {
                openCategoriesScreenEvent.postValue(Unit)
            } else {
                openHighlightsScreenEvent.postValue(Unit)
            }
        }
    }
}