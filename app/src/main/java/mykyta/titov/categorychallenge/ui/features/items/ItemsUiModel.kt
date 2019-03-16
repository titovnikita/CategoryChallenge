package mykyta.titov.categorychallenge.ui.features.items

import mykyta.titov.categorychallenge.domain.Item


data class ItemsUiModel(val state: State)

sealed class State {
    class Loaded(val items: List<Item>) : State()

    object Error : State()
}