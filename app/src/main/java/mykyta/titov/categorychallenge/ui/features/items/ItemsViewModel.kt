package mykyta.titov.categorychallenge.ui.features.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mykyta.titov.categorychallenge.core.SingleLiveEvent
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.domain.Item
import mykyta.titov.categorychallenge.ui.base.BaseViewModel
import mykyta.titov.categorychallenge.usecases.GetItemsUseCase
import java.util.concurrent.Executor

class ItemsViewModel(
        private val getItemsUseCase: GetItemsUseCase,
        private val executor: Executor
) : BaseViewModel() {

    private val openImageScreenEvent = SingleLiveEvent<String>()

    private val uiModel: MutableLiveData<ItemsUiModel> = MutableLiveData()

    fun openImageScreenEvents(): SingleLiveEvent<String> = openImageScreenEvent

    fun uiEvents(): LiveData<ItemsUiModel> = uiModel

    fun start(category: Category) {
        executor.execute {
            try {
                val items = getItemsUseCase.getItems(category.id)
                uiModel.postValue(ItemsUiModel(State.Loaded(items)))
            } catch (exception: IllegalStateException) {
                uiModel.postValue(ItemsUiModel(State.Error))
            }
        }
    }

    fun onItemClicked(item: Item) {
        openImageScreenEvent.value = item.imageUrl
    }
}