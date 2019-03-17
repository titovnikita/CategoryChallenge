package mykyta.titov.categorychallenge.core

import android.app.Application
import android.content.ContentResolver
import mykyta.titov.categorychallenge.core.providers.*
import mykyta.titov.categorychallenge.core.providers.mappers.CategoriesMapperProvider
import mykyta.titov.categorychallenge.core.providers.mappers.ItemsMapperProvider
import mykyta.titov.categorychallenge.core.providers.repository.categories.CategoriesRemoteProvider
import mykyta.titov.categorychallenge.core.providers.repository.categories.CategoriesRepositoryProvider
import mykyta.titov.categorychallenge.core.providers.repository.items.ItemsRemoteProvider
import mykyta.titov.categorychallenge.core.providers.repository.items.ItemsRepositoryProvider
import mykyta.titov.categorychallenge.core.providers.repository.tracking.TrackingLocalProvider
import mykyta.titov.categorychallenge.core.providers.repository.tracking.TrackingRepositoryProvider
import mykyta.titov.categorychallenge.core.providers.usecases.GetCategoriesUseCaseProvider
import mykyta.titov.categorychallenge.core.providers.usecases.GetItemsUseCaseProvider
import mykyta.titov.categorychallenge.core.providers.usecases.UpdatePopularityUseCaseProvider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.data.mappers.ItemsMapper
import mykyta.titov.categorychallenge.data.repositories.categories.CategoriesRepository
import mykyta.titov.categorychallenge.data.repositories.items.ItemsRepository
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import mykyta.titov.categorychallenge.usecases.GetCategoriesUseCase
import mykyta.titov.categorychallenge.usecases.GetItemsUseCase
import mykyta.titov.categorychallenge.usecases.UpdatePopularityUseCase
import retrofit2.Retrofit
import java.util.concurrent.Executor

class App : Application(), ApplicationBridge {

    private lateinit var viewModelFactoryProvider: Provider<ViewModelFactory>

    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        val retrofitProvider: Provider<Retrofit> = RetrofitProvider()

        val executorProvider: Provider<Executor> = ExecutorProvider()

        val categoriesMapperProvider: Provider<CategoriesMapper> = CategoriesMapperProvider()

        val itemsMapperProvider: Provider<ItemsMapper> = ItemsMapperProvider()

        val contentResolverProvider: Provider<ContentResolver> =
                ContentResolverProvider(contentResolver)

        val categoriesRemoteProvider: Provider<CategoriesRepository.Remote> =
                CategoriesRemoteProvider(retrofitProvider)

        val itemsRemoteProvider: Provider<ItemsRepository.Remote> =
                ItemsRemoteProvider(retrofitProvider)

        val trackingLocalProvider: Provider<TrackingRepository.Local> =
                TrackingLocalProvider(contentResolverProvider)

        val categoriesRepositoryProvider: Provider<CategoriesRepository> =
                CategoriesRepositoryProvider(categoriesRemoteProvider)

        val trackingRepositoryProvider: Provider<TrackingRepository> =
                TrackingRepositoryProvider(trackingLocalProvider)

        val itemsRepositoryProvider: Provider<ItemsRepository> =
                ItemsRepositoryProvider(itemsRemoteProvider)

        val getCategoriesUseCaseProvider: Provider<GetCategoriesUseCase> =
                GetCategoriesUseCaseProvider(
                        categoriesRepositoryProvider,
                        categoriesMapperProvider
                )

        val getItemsUseCaseProvider: Provider<GetItemsUseCase> =
                GetItemsUseCaseProvider(
                        itemsRepositoryProvider,
                        itemsMapperProvider
                )

        val updatePopularityUseCaseProvider: Provider<UpdatePopularityUseCase> =
                UpdatePopularityUseCaseProvider(
                        trackingRepositoryProvider,
                        categoriesMapperProvider
                )

        this.viewModelFactoryProvider =
                ViewModelFactoryProvider(
                        executorProvider,
                        getCategoriesUseCaseProvider,
                        getItemsUseCaseProvider,
                        updatePopularityUseCaseProvider
                )
    }

    override fun getViewModelFactory() = viewModelFactoryProvider.get()
}