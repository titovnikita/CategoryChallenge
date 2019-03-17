package mykyta.titov.categorychallenge.core.providers.repository.tracking

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository

class TrackingRepositoryProvider(
        private val trackingLocalProvider: Provider<TrackingRepository.Local>
) : Provider<TrackingRepository>() {

    private val trackingRepository: TrackingRepository by lazy {
        TrackingRepository(
                localDataSource = trackingLocalProvider.get()
        )
    }

    override fun get(): TrackingRepository = trackingRepository
}