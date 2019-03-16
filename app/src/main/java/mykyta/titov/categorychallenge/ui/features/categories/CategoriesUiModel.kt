package mykyta.titov.categorychallenge.ui.features.categories

import mykyta.titov.categorychallenge.domain.Category

data class CategoriesUiModel(val state: State)

sealed class State {
    class Loaded(val categories: List<Category>) : State()

    object Error : State()
}