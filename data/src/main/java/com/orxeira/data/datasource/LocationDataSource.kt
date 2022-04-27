package com.orxeira.data.datasource

interface LocationDataSource {
    suspend fun findLastLocation(): String?
}
