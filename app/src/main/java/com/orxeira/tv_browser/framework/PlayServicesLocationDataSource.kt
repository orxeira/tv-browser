package com.orxeira.tv_browser.framework

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.orxeira.data.datasource.LocationDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(application: Application) : LocationDataSource {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)


    @SuppressLint("MissingPermission")
    override suspend fun findLastLocation(): String? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result.toLanguage())
                }
        }

    private fun Location?.toLanguage(): String {
        val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.locale.toString()
    }
}