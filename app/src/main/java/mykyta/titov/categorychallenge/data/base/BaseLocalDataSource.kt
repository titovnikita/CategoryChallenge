package mykyta.titov.categorychallenge.data.base

import android.content.ContentResolver

abstract class BaseLocalDataSource(
        protected val contentResolver: ContentResolver
)
