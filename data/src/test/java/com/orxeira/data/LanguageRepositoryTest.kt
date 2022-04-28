package com.orxeira.data

import com.orxeira.data.PermissionChecker.Permission.COARSE_LOCATION
import com.orxeira.data.datasource.LanguageDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LanguageRepositoryTest {

    @Test
    fun `Returns default language when coarse permission is not granted`(): Unit =
        runBlocking {
            val regionRepository = buildLanguageRepository(
                permissionChecker = mock { on { check(COARSE_LOCATION) } doReturn false }
            )

            val result = regionRepository.findLastLanguage()

            Assert.assertEquals(LanguageRepository.DEFAULT_LANGUAGE, result)
        }

    @Test
    fun `Returns language when coarse permission is granted`(): Unit =
        runBlocking {
            val regionRepository = buildLanguageRepository(
                languageDataSource = mock { onBlocking { findLastLanguage() } doReturn "en_US" },
                permissionChecker = mock { on { check(COARSE_LOCATION) } doReturn true }
            )

            val result = regionRepository.findLastLanguage()

            Assert.assertEquals("en_US", result)
        }

    @Test
    fun `Returns default language in coarse permission is given but findLanguage is null`(): Unit =
        runBlocking {
            val regionRepository = buildLanguageRepository(
                languageDataSource = mock { onBlocking { findLastLanguage() } doReturn null },
                permissionChecker = mock { on { check(COARSE_LOCATION) } doReturn true }
            )

            val result = regionRepository.findLastLanguage()

            Assert.assertEquals(LanguageRepository.DEFAULT_LANGUAGE, result)
        }

    private fun buildLanguageRepository(
        languageDataSource: LanguageDataSource = mock(),
        permissionChecker: PermissionChecker = mock()
    ) = LanguageRepository(languageDataSource, permissionChecker)

}