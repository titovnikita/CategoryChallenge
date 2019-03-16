package mykyta.titov.tracking.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mykyta.titov.tracking.data.tables.CategoriesTable

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(CategoriesTable.CREATE_CATEGORIES_TABLE)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}

private const val VERSION = 1
private const val NAME = "categories.db"

const val AUTHORITY = "com.categories.provider"
