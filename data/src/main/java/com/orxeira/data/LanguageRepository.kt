package com.orxeira.data

import com.orxeira.data.datasource.LanguageDataSource

class LanguageRepository(private val languageDataSource: LanguageDataSource,
                         private val permissionChecker: PermissionChecker) {

    companion object {
        const val DEFAULT_LANGUAGE = "es_ES"
    }

    suspend fun findLastLanguage(): String {
        return if (permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)) {
            languageDataSource.findLastLanguage() ?: DEFAULT_LANGUAGE
        } else {
            DEFAULT_LANGUAGE
        }
    }
}