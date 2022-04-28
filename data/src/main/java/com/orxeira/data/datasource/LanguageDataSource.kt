package com.orxeira.data.datasource

interface LanguageDataSource {
    suspend fun findLastLanguage(): String?
}
