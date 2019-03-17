package mykyta.titov.categorychallenge.data.repositories.tracking

import android.content.ContentResolver
import android.database.Cursor
import mykyta.titov.categorychallenge.data.base.BaseLocalDataSource
import mykyta.titov.tracking.data.entities.TrackingEntity
import mykyta.titov.tracking.data.tables.TrackingTable


class TrackingRepository(private val localDataSource: Local) {

    fun queryByPopularity(itemsCount: Int): Cursor? = localDataSource.queryByPopularity(itemsCount)

    fun incrementPopularity(trackingEntity: TrackingEntity) = localDataSource.incrementPopularity(trackingEntity)

    class Local(contentResolver: ContentResolver) : BaseLocalDataSource(contentResolver) {

        fun queryByPopularity(itemsCount: Int): Cursor? {
            val sortOrder = "${TrackingTable.POPULARITY} DESC LIMIT $itemsCount"
            return contentResolver.query(TrackingTable.CONTENT_URI, null, null, null, sortOrder)
        }

        fun incrementPopularity(trackingEntity: TrackingEntity) {
            val where = "${TrackingTable.CATEGORY_ID} = ?"
            val arguments = arrayOf(trackingEntity.categoryId)

            val cursor: Cursor? = contentResolver.query(TrackingTable.CONTENT_URI, null, where, arguments, null)
            if (cursor != null && cursor.count > 0) {
                cursor.apply {
                    if (moveToFirst()) {
                        val entity = TrackingEntity(cursor)
                                .apply {
                                    popularity = popularity.inc()
                                }

                        contentResolver.update(TrackingTable.CONTENT_URI, entity.toContentValues(), where, arguments)
                    }

                    close()
                }
            } else {
                contentResolver.insert(
                        TrackingTable.CONTENT_URI,
                        trackingEntity
                                .apply {
                                    popularity = popularity.inc()
                                }.toContentValues()
                )
            }
        }
    }
}

