package mykyta.titov.tracking.data.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import mykyta.titov.tracking.data.tables.CategoriesTable


class DatabaseContentProvider : ContentProvider() {

    private lateinit var databaseHelper: DatabaseHelper

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            .apply {
                addURI(AUTHORITY, CategoriesTable.TABLE_NAME, CATEGORIES)
                addURI(AUTHORITY, "${CategoriesTable.TABLE_NAME}/#", CATEGORIES_CATEGORY)
            }

    override fun onCreate(): Boolean = true
            .also {
                context?.apply {
                    databaseHelper = DatabaseHelper(this)
                }
            }

    override fun query(uri: Uri, projection: Array<String>?, selection: String,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor {
        val tableName = getTableName(uri)
        val selectionToAppend = when {
            isItem(uriMatcher.match(uri)) -> getSelectionToAppend(uri)
            else -> ""
        }

        val database = databaseHelper.writableDatabase
        val cursor = database.query(tableName, projection, appendSelections(selection, selectionToAppend),
                selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val database = databaseHelper.writableDatabase
        val result = database.insertWithOnConflict(getTableName(uri), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
                .also { context?.contentResolver?.notifyChange(uri, null) }
        return Uri.withAppendedPath(uri, result.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val tableName = getTableName(uri)
        val selectionToAppend = when {
            isItem(uriMatcher.match(uri)) -> getSelectionToAppend(uri)
            else -> ""
        }

        val database = databaseHelper.writableDatabase
        val affected = database.delete(tableName, appendSelections(selection, selectionToAppend), selectionArgs)
        if (affected > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return affected
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val tableName = getTableName(uri)
        val selectionToAppend = when {
            isItem(uriMatcher.match(uri)) -> getSelectionToAppend(uri)
            else -> ""
        }

        val database = databaseHelper.writableDatabase
        val affected = database.update(tableName, values, appendSelections(selection, selectionToAppend), selectionArgs)
        if (affected > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return affected
    }

    override fun getType(uri: Uri): String = when (uriMatcher.match(uri)) {
        CATEGORIES -> DIR + AUTHORITY + "." + CategoriesTable.TABLE_NAME
        CATEGORIES_CATEGORY -> ITEM + AUTHORITY + "." + CategoriesTable.TABLE_NAME
        else -> throw IllegalArgumentException("Cannot match type with URL matcher!")
    }

    private fun isItem(uriItem: Int): Boolean =
            uriItem == CATEGORIES_CATEGORY

    private fun appendSelections(baseSelection: String?, selectionToAppend: String?): String =
            when {
                selectionToAppend != null
                        && baseSelection != null
                        && selectionToAppend.isNotEmpty()
                        && baseSelection.isNotEmpty() -> "($baseSelection) AND $selectionToAppend"
                selectionToAppend != null && selectionToAppend.isNotEmpty() -> selectionToAppend
                else -> ""

            }

    private fun getTableName(uri: Uri): String =
            when (uriMatcher.match(uri)) {
                CATEGORIES -> CategoriesTable.TABLE_NAME
                CATEGORIES_CATEGORY -> CategoriesTable.TABLE_NAME
                else -> throw IllegalArgumentException("Unable to get table name!")
            }

    private fun getSelectionToAppend(uri: Uri): String =
            when (uriMatcher.match(uri)) {
                CATEGORIES_CATEGORY -> "${CategoriesTable.ID} = ${uri.lastPathSegment}"
                else -> throw IllegalArgumentException("Cannot get selection to append!")
            }
}

private const val CATEGORIES = 1
private const val CATEGORIES_CATEGORY = 2

private const val DIR = "vnd.android.cursor.dir/"
private const val ITEM = "vnd.android.cursor.item/"