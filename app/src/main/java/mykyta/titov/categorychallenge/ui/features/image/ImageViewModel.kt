package mykyta.titov.categorychallenge.ui.features.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mykyta.titov.categorychallenge.ui.base.BaseViewModel


class ImageViewModel : BaseViewModel() {

    private val uiModel: MutableLiveData<ImageUiModel> = MutableLiveData()

    fun uiEvents(): LiveData<ImageUiModel> = uiModel

    fun start(imageUrl: String) {
        uiModel.value = ImageUiModel(imageUrl)
    }
}