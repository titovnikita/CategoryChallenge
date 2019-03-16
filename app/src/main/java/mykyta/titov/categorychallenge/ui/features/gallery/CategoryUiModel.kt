package mykyta.titov.categorychallenge.ui.features.gallery

import mykyta.titov.categorychallenge.domain.Category

data class CategoryUiModel(val state: State)

sealed class State {
    class Loaded(val categories: List<Category>) : State()

    object Error : State()
}