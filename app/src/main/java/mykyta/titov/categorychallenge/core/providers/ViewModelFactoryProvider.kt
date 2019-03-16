package mykyta.titov.categorychallenge.core.providers

import mykyta.titov.categorychallenge.core.ViewModelFactory
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import java.util.concurrent.Executor

class ViewModelFactoryProvider(
        private val executorProvider: Provider<Executor>,
        private val getCategoriesUseCaseProvider: Provider<GetCategoriesUseCase>
) : Provider<ViewModelFactory>() {

    private val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(
                executor = executorProvider.get(),
                getCategoriesUseCase = getCategoriesUseCaseProvider.get()
        )
    }

    override fun get(): ViewModelFactory = viewModelFactory
}