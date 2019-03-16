package mykyta.titov.tracking.data.entities

import android.content.ContentValues

interface Entity {
    fun toContentValues(): ContentValues
}