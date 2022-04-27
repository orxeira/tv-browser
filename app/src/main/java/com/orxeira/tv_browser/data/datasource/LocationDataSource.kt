package com.orxeira.tv_browser.data.datasource

import android.location.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}
