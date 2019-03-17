package mykyta.titov.categorychallenge.core.providers.repository.tracking

import android.content.ContentResolver
import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository

class TrackingLocalProvider(
        private val contentResolverProvider: Provider<ContentResolver>
) : Provider<TrackingRepository.Local>() {

    private val local: TrackingRepository.Local by lazy {
        TrackingRepository.Local(
                contentResolver = contentResolverProvider.get()
        )
    }

    override fun get(): TrackingRepository.Local = local
}