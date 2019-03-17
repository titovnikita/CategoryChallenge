package mykyta.titov.categorychallenge.core.providers

import android.content.ContentResolver

class ContentResolverProvider(
        private val contentResolver: ContentResolver
) : Provider<ContentResolver>() {

    override fun get(): ContentResolver = contentResolver
}