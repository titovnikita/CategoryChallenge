package mykyta.titov.categorychallenge.domain

class Category(
        val id: String,
        val cover: Image
)

class Image(
        val full: String,
        val regular: String
)