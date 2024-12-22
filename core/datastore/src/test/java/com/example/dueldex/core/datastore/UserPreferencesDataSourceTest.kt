package com.example.dueldex.core.datastore

import com.example.dueldex.core.datastore.test.InMemoryDataStore
import com.example.dueldex.core.model.UserEnvData
import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UserPreferencesDataSourceTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: UserPreferencesDataSource

    @Before
    fun setup() {
        subject = UserPreferencesDataSource(InMemoryDataStore(UserPreferences.getDefaultInstance()))
    }

    @Test
    fun userThemeConfigChange() = testScope.runTest {
        assertEquals(subject.userEnvData.first().themeConfig, ThemeConfig.FOLLOW_SYSTEM)
        subject.setThemeConfig(ThemeConfig.DARK)
        assertEquals(subject.userEnvData.first().themeConfig, ThemeConfig.DARK)
    }

    @Test
    fun userLocalizationConfigChange() = testScope.runTest {
        assertEquals(
            subject.userEnvData.first().localizationConfig,
            LocalizationConfig.FOLLOW_SYSTEM
        )
        subject.setLocalizationConfig(LocalizationConfig.KOREAN)
        assertEquals(subject.userEnvData.first().localizationConfig, LocalizationConfig.KOREAN)
    }

    @Test
    fun addRecentSearchTest() = testScope.runTest {
        assertEquals(subject.getRecentSearchList.first(), listOf())
        subject.addRecentSearch("Search Text 1")
        subject.addRecentSearch("Search Text 2")
        assertEquals(subject.getRecentSearchList.first(), listOf("Search Text 1", "Search Text 2"))
    }

    @Test
    fun addAndDeleteRecentSearchTest() = testScope.runTest {
        subject.addRecentSearch("Search Text 1")
        subject.addRecentSearch("Search Text 2")
        assertEquals(subject.getRecentSearchList.first(), listOf("Search Text 1", "Search Text 2"))
        subject.removeRecentSearch("Search Text 2")
        assertEquals(subject.getRecentSearchList.first(), listOf("Search Text 1"))
    }

    @Test
    fun userPreferencesIntegrationTest() = testScope.runTest {
        subject.setThemeConfig(ThemeConfig.LIGHT)
        subject.setLocalizationConfig(LocalizationConfig.ENGLISH)
        subject.addRecentSearch("Search Text 1")
        subject.addRecentSearch("Search Text 2")
        assertEquals(
            subject.userEnvData.first(),
            UserEnvData(
                themeConfig = ThemeConfig.LIGHT,
                localizationConfig = LocalizationConfig.ENGLISH
            )
        )
        assertEquals(
            subject.getRecentSearchList.first(),
            listOf("Search Text 1", "Search Text 2")
        )
    }
}