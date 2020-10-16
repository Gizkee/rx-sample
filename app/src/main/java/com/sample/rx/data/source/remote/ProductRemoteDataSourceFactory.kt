package com.sample.rx.data.source.remote

import ProductRemoteDataSource

class ProductRemoteDataSourceFactory(
        private val settingsRepository: SettingsRepository,
        private val sampleRemoteDataSource: SampleRemoteDataSource
) {

    fun create(): ProductRemoteDataSource {
        return when (settingsRepository.vendor) {
            SampleRemoteDataSource.NAME -> sampleRemoteDataSource
            else -> throw VendorNotConfiguredException()
        }
    }

}