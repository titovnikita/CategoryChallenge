package mykyta.titov.tracking.data.tables

import android.net.Uri
import mykyta.titov.tracking.data.database.AUTHORITY

object CategoriesTable {
    val TABLE_NAME = "Categories"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY/$TABLE_NAME")

    val ID = "_id"
    val ID_CATEGORY = "id_category"
    val POPULARITY = "popularity"

    val CREATE_CATEGORIES_TABLE = (
            "CREATE TABLE "
                    + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ID_CATEGORY + " TEXT,"
                    + POPULARITY + " INTEGER"
                    + ")"
            )
}