package mykyta.titov.tracking.data.tables

import android.net.Uri
import mykyta.titov.tracking.data.database.AUTHORITY

object TrackingTable {
    val TABLE_NAME = "Tracking"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY/$TABLE_NAME")

    val ID = "_id"
    val CATEGORY_ID = "id_category"
    val CATEGORY_IMAGE_URL = "image_category_url"
    val POPULARITY = "popularity"

    val CREATE_TRACKING_TABLE = (
            "CREATE TABLE "
                    + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CATEGORY_ID + " TEXT,"
                    + CATEGORY_IMAGE_URL + " TEXT,"
                    + POPULARITY + " INTEGER"
                    + ")"
            )
}