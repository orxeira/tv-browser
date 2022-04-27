package com.orxeira.data

import com.orxeira.data.datasource.LocationDataSource

class RegionRepository(private val locationDataSource: LocationDataSource,
                       private val permissionChecker: PermissionChecker) {

    companion object {
        private const val DEFAULT_LANGUAGE = "es_ES"
    }

    suspend fun findLastLanguage(): String {
        return if (permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)) {
            locationDataSource.findLastLocation() ?: DEFAULT_LANGUAGE
        } else {
            DEFAULT_LANGUAGE
        }
    }
}