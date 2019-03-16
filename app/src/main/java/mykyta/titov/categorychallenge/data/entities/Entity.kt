package mykyta.titov.categorychallenge.data.entities

import android.content.ContentValues

interface Entity {
    fun toContentValues(): ContentValues
}