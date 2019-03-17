package mykyta.titov.categorychallenge.core.providers.usecases

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.usecases.SortCategoriesUseCase

class SortCategoriesUseCaseProvider : Provider<SortCategoriesUseCase>() {

    private val sortCategoriesUseCase: SortCategoriesUseCase by lazy {
        SortCategoriesUseCase()
    }

    override fun get(): SortCategoriesUseCase = sortCategoriesUseCase
}