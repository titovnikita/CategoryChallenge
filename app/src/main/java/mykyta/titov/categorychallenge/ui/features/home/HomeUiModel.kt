package mykyta.titov.categorychallenge.ui.features.home

data class HomeUiModel(val state: State)

sealed class State {
    object ShowMostViewedCategory : State()
    object ShowCategories : State()
}