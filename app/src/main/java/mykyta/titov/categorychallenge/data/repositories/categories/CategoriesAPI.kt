package mykyta.titov.categorychallenge.data.repositories.categories

import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CategoriesAPI {

    @GET("/collections")
    fun getCategories(@Query("per_page") itemsCount: Int): Call<List<CategoryDto>>
}