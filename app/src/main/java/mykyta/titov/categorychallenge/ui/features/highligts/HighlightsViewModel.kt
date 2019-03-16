package mykyta.titov.categorychallenge.ui.features.highligts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseViewModel


class HighlightsViewModel : BaseViewModel() {

    private val openCategoriesScreenEvent = SingleLiveEvent<Unit>()

    fun openCategoriesScreenEvents(): SingleLiveEvent<Unit> = openCategoriesScreenEvent

    private val uiModel: MutableLiveData<HighlightsUiModel> = MutableLiveData()

    fun uiEvents(): LiveData<HighlightsUiModel> = uiModel

    fun start(category: Category) {
        uiModel.value = HighlightsUiModel(category.imageUrl)
    }

    fun onCategoriesClicked() {
        openCategoriesScreenEvent.call()
    }
}