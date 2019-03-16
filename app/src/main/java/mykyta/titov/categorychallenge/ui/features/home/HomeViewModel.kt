package mykyta.titov.categorychallenge.ui.features.home

import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.ui.base.BaseViewModel


class HomeViewModel : BaseViewModel() {

    private val openHighlightsScreenEvent = SingleLiveEvent<Unit>()

    private val openCategoriesScreenEvent = SingleLiveEvent<Unit>()

    fun openHighlightsScreenEvents(): SingleLiveEvent<Unit> = openHighlightsScreenEvent

    fun openCategoriesScreenEvents(): SingleLiveEvent<Unit> = openCategoriesScreenEvent

    fun start() {
//        openCategoriesScreenEvent.call()
        openHighlightsScreenEvent.call()
    }
}