package mykyta.titov.tracking.data.entities

import android.content.ContentValues
import android.database.Cursor
import mykyta.titov.tracking.data.tables.TrackingTable


class TrackingEntity : Entity {

    val id: Int?
    val categoryId: String
    val categoryImageUrl: String
    var popularity: Int

    constructor(id: Int? = null,
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
            this@TrackingEntity.id = getInt(getColumnIndex(TrackingTable.ID))
            this@TrackingEntity.categoryId = getString(getColumnIndex(TrackingTable.CATEGORY_ID))
            this@TrackingEntity.categoryImageUrl = getString(getColumnIndex(TrackingTable.CATEGORY_IMAGE_URL))
            this@TrackingEntity.popularity = getInt(getColumnIndex(TrackingTable.POPULARITY))
        }
    }

    override fun toContentValues() =
            ContentValues().apply {
                put(TrackingTable.ID, id)
                put(TrackingTable.CATEGORY_ID, categoryId)
                put(TrackingTable.CATEGORY_IMAGE_URL, categoryImageUrl)
                put(TrackingTable.POPULARITY, popularity)
            }
}