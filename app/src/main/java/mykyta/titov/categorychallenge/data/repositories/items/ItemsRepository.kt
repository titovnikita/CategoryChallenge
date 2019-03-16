package mykyta.titov.categorychallenge.data.repositories.items

import mykyta.titov.categorychallenge.data.base.BaseRemoteDataSource
import mykyta.titov.categorychallenge.data.dtos.ItemDto
import retrofit2.Call
import retrofit2.Retrofit

class ItemsRepository(private val remoteDataSource: Remote) {

    fun getItems(id: String): Call<List<ItemDto>> = remoteDataSource.getItems(id)

    class Remote(retrofit: Retrofit) : BaseRemoteDataSource(retrofit) {

        private val service: ItemsAPI by lazy {
            createService(ItemsAPI::class.java)
        }

        fun getItems(id: String): Call<List<ItemDto>> = service.getItems(id)
    }
}