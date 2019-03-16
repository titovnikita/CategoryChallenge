package mykyta.titov.categorychallenge.core

import android.app.Application
import mykyta.titov.categorychallenge.core.providers.ExecutorProvider
import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.core.providers.RetrofitProvider
import mykyta.titov.categorychallenge.core.providers.ViewModelFactoryProvider
import mykyta.titov.categorychallenge.core.providers.mappers.CategoriesMapperProvider
import mykyta.titov.categorychallenge.core.providers.repository.CategoriesRemoteProvider
import mykyta.titov.categorychallenge.core.providers.repository.CategoriesRepositoryProvider
import mykyta.titov.categorychallenge.core.providers.usecases.GetCategoriesUseCaseProvider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.repositories.details.CategoriesRepository
import mykyta.titov.categorychallenge.data.repositories.details.Remote
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import mykyta.titov.tracking.data.database.DatabaseHelper
import retrofit2.Retrofit
import java.util.concurrent.Executor

class App : Application(), ApplicationBridge {

    private val retrofitProvider: Provider<Retrofit> = RetrofitProvider()

    private val executorProvider: Provider<Executor> = ExecutorProvider()

    private val categoriesMapperProvider: Provider<CategoriesMapper> = CategoriesMapperProvider()

    private val categoriesRemoteProvider: Provider<Remote> = CategoriesRemoteProvider(retrofitProvider)

    private val categoriesRepositoryProvider: Provider<CategoriesRepository> = CategoriesRepositoryProvider(categoriesRemoteProvider)

    private val getCategoriesUseCaseProvider: Provider<GetCategoriesUseCase> = GetCategoriesUseCaseProvider(categoriesRepositoryProvider, categoriesMapperProvider)

    private val viewModelFactoryProvider: Provider<ViewModelFactory> = ViewModelFactoryProvider(executorProvider, getCategoriesUseCaseProvider)

    override fun getViewModelFactory() = viewModelFactoryProvider.get()
}