package mykyta.titov.categorychallenge.data.entities

import android.content.ContentValues
import android.database.Cursor
import mykyta.titov.categorychallenge.data.tables.CategoriesTable


class CategoryEntity : Entity {

    private val id: Int
    private val categoryId: String
    private val popularity: Int

    constructor(id: Int,
                categoryId: String,
                popularity: Int) {
        this.id = id
        this.categoryId = categoryId
        this.popularity = popularity
    }

    constructor(cursor: Cursor) {
        with(cursor) {
            this@CategoryEntity.id = getInt(getColumnIndex(CategoriesTable.ID))
            this@CategoryEntity.categoryId = getString(getColumnIndex(CategoriesTable.ID_CATEGORY))
            this@CategoryEntity.popularity = getInt(getColumnIndex(CategoriesTable.POPULARITY))
        }
    }

    override fun toContentValues() =
            ContentValues().apply {
                put(CategoriesTable.ID, id)
                put(CategoriesTable.ID_CATEGORY, categoryId)
                put(CategoriesTable.POPULARITY, popularity)
            }
}