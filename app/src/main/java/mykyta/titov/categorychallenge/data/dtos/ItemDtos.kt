package mykyta.titov.categorychallenge.data.dtos

import com.google.gson.annotations.SerializedName

class ItemDto(
        @SerializedName("id") val id: String?,
        @SerializedName("urls") val urls: UrlsDto?
)