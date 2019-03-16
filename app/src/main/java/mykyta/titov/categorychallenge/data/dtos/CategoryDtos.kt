package mykyta.titov.categorychallenge.data.dtos

import com.google.gson.annotations.SerializedName

class CategoryDto(
        @SerializedName("id") val id: String,
        @SerializedName("cover_photo") val coverPhoto: CoverPhotoDto

)

class CoverPhotoDto(
        @SerializedName("urls") val urls: UrlsDto?
)

class UrlsDto(
        @SerializedName("full") val full: String?,
        @SerializedName("regular") val regular: String?
)