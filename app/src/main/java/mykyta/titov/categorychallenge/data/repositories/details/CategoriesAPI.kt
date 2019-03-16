package mykyta.titov.categorychallenge.data.repositories.details

import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface CategoriesAPI {

    @GET("/collections")
    fun getCategories(): Call<List<CategoryDto>>
}