package mykyta.titov.tracking.data.entities

import android.content.ContentValues
import android.database.Cursor
import mykyta.titov.tracking.data.tables.CategoriesTable


class CategoryEntity : Entity {

    private val id: Int

    val categoryId: String
    val categoryImageUrl: String
    val popularity: Int

    constructor(id: Int,
                categoryId: String,
                categoryImageUrl: String,
                popularity: Int) {
        this.id = id
        this.categoryId = categoryId
        this.categoryImageUrl = categoryImageUrl
        this.popularity = popularity
    }

    constructor(cursor: Cursor) {
        with(cursor) {
            this@CategoryEntity.id = getInt(getColumnIndex(CategoriesTable.ID))
            this@CategoryEntity.categoryId = getString(getColumnIndex(CategoriesTable.CATEGORY_ID))
            this@CategoryEntity.categoryImageUrl = getString(getColumnIndex(CategoriesTable.CATEGORY_IMAGE_URL))
            this@CategoryEntity.popularity = getInt(getColumnIndex(CategoriesTable.POPULARITY))
        }
    }

    override fun toContentValues() =
            ContentValues().apply {
                put(CategoriesTable.ID, id)
                put(CategoriesTable.CATEGORY_ID, categoryId)
                put(CategoriesTable.CATEGORY_IMAGE_URL, categoryImageUrl)
                put(CategoriesTable.POPULARITY, popularity)
            }
}