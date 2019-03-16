package mykyta.titov.categorychallenge.core

import android.app.Application
import mykyta.titov.categorychallenge.core.providers.ExecutorProvider
import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.core.providers.RetrofitProvider
import mykyta.titov.categorychallenge.core.providers.ViewModelFactoryProvider
import mykyta.titov.categorychallenge.core.providers.mappers.CategoriesMapperProvider
import mykyta.titov.categorychallenge.core.providers.mappers.ItemsMapperProvider
import mykyta.titov.categorychallenge.core.providers.repository.categories.CategoriesRemoteProvider
import mykyta.titov.categorychallenge.core.providers.repository.categories.CategoriesRepositoryProvider
import mykyta.titov.categorychallenge.core.providers.repository.items.ItemsRemoteProvider
import mykyta.titov.categorychallenge.core.providers.repository.items.ItemsRepositoryProvider
import mykyta.titov.categorychallenge.core.providers.usecases.GetCategoriesUseCaseProvider
import mykyta.titov.categorychallenge.core.providers.usecases.GetItemsUseCaseProvider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.mappers.ItemsMapper
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import mykyta.titov.categorychallenge.usecases.GetItemsUseCase
import retrofit2.Retrofit
import java.util.concurrent.Executor

class App : Application(), ApplicationBridge {

    private val retrofitProvider: Provider<Retrofit> = RetrofitProvider()

    private val executorProvider: Provider<Executor> = ExecutorProvider()

    private val categoriesMapperProvider: Provider<CategoriesMapper> = CategoriesMapperProvider()

    private val itemsMapperProvider: Provider<ItemsMapper> = ItemsMapperProvider()

    private val categoriesRemoteProvider: Provider<CategoriesRepository.Remote> = CategoriesRemoteProvider(retrofitProvider)

    private val itemsRemoteProvider: Provider<ItemsRepository.Remote> = ItemsRemoteProvider(retrofitProvider)

    private val categoriesRepositoryProvider: Provider<CategoriesRepository> = CategoriesRepositoryProvider(categoriesRemoteProvider)

    private val itemsRepositoryProvider: Provider<ItemsRepository> = ItemsRepositoryProvider(itemsRemoteProvider)

    private val getCategoriesUseCaseProvider: Provider<GetCategoriesUseCase> = GetCategoriesUseCaseProvider(categoriesRepositoryProvider, categoriesMapperProvider)

    private val getItemsUseCaseProvider: Provider<GetItemsUseCase> = GetItemsUseCaseProvider(itemsRepositoryProvider, itemsMapperProvider)

    private val viewModelFactoryProvider: Provider<ViewModelFactory> = ViewModelFactoryProvider(executorProvider, getCategoriesUseCaseProvider, getItemsUseCaseProvider)

    override fun getViewModelFactory() = viewModelFactoryProvider.get()
}