package mykyta.titov.categorychallenge.data.repositories.categories

import mykyta.titov.categorychallenge.data.base.BaseRemoteDataSource
import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import retrofit2.Call
import retrofit2.Retrofit

class CategoriesRepository(private val remoteDataSource: Remote) {

    fun getCategories(itemsCount: Int): Call<List<CategoryDto>> = remoteDataSource.getCategories(itemsCount)

    class Remote(retrofit: Retrofit) : BaseRemoteDataSource(retrofit) {

        private val service: CategoriesAPI by lazy {
            createService(CategoriesAPI::class.java)
        }

        fun getCategories(itemsCount: Int): Call<List<CategoryDto>> = service.getCategories(itemsCount)
    }
}