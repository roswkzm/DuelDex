package com.example.dueldex.core.data.repository

import com.example.dueldex.core.datastore.UserPreferencesDataSource
import com.example.dueldex.core.model.UserEnvData
import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserDataRepositoryImpl @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : UserDataRepository {

    override val recentSearchList: Flow<List<String>> =
        userPreferencesDataSource.getRecentSearchList.map { it.reversed() }

    override suspend fun addRecentSearch(searchText: String) {
        userPreferencesDataSource.addRecentSearch(searchText)
    }

    override suspend fun removeRecentSearch(searchText: String) {
        userPreferencesDataSource.removeRecentSearch(searchText)
    }

    override suspend fun clearAllRecentSearches() {
        userPreferencesDataSource.clearAllRecentSearches()
    }

    override val userEnvData: Flow<UserEnvData> =
        userPreferencesDataSource.userEnvData

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        userPreferencesDataSource.setThemeConfig(themeConfig)
    }

    override suspend fun setLocalizationConfig(localizationConfig: LocalizationConfig) {
        userPreferencesDataSource.setLocalizationConfig(localizationConfig)
    }
}