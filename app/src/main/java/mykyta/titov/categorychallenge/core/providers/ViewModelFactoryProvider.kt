package mykyta.titov.categorychallenge.core.providers

import mykyta.titov.categorychallenge.core.ViewModelFactory
import mykyta.titov.categorychallenge.usecases.*
import java.util.concurrent.Executor

class ViewModelFactoryProvider(
        private val executorProvider: Provider<Executor>,
        private val getCategoriesUseCaseProvider: Provider<GetCategoriesUseCase>,
        private val getItemsUseCaseProvider: Provider<GetItemsUseCase>,
        private val updatePopularityUseCaseProvider: Provider<UpdatePopularityUseCase>,
        private val getTopCategoryUseCaseProvider: Provider<GetTopCategoryUseCase>,
        private val sortCategoriesUseCaseProvider: Provider<SortCategoriesUseCase>
) : Provider<ViewModelFactory>() {

    private val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(
                executor = executorProvider.get(),
                getCategoriesUseCase = getCategoriesUseCaseProvider.get(),
                getItemsUseCase = getItemsUseCaseProvider.get(),
                updatePopularityUseCase = updatePopularityUseCaseProvider.get(),
                getTopCategoryUseCase = getTopCategoryUseCaseProvider.get(),
                sortCategoriesUseCase = sortCategoriesUseCaseProvider.get()
        )
    }

    override fun get(): ViewModelFactory = viewModelFactory
}