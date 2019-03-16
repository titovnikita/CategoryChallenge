package mykyta.titov.categorychallenge.data.repositories.items

import mykyta.titov.categorychallenge.data.dtos.ItemDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ItemsAPI {

    @GET("/collections/{id}/photos")
    fun getItems(@Path("id") id: String): Call<List<ItemDto>>
}