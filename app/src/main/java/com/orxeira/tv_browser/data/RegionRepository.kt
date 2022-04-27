package com.orxeira.tv_browser.data

import android.Manifest
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.orxeira.tv_browser.data.datasource.LocationDataSource
import com.orxeira.tv_browser.framework.datasource.PlayServicesLocationDataSource

class RegionRepository(application: Application) {

    companion object {
        private const val DEFAULT_LANGUAGE = "es_ES"
    }

    private val locationDataSource: LocationDataSource = PlayServicesLocationDataSource(application)
    private val coarsePermissionChecker = PermissionChecker(
        application,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private val geocoder = Geocoder(application)

    suspend fun findLastLanguage(): String = findLastLocation().toLanguage()

    private suspend fun findLastLocation(): Location? {
        val success = coarsePermissionChecker.check()
        return if (success) locationDataSource.findLastLocation() else null
    }

    private fun Location?.toLanguage(): String {
        val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.locale?.toString() ?: DEFAULT_LANGUAGE
    }
}