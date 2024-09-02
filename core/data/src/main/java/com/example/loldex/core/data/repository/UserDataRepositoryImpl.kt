package com.example.loldex.core.data.repository

import com.example.loldex.core.datastore.UserPreferencesDataSource
import com.example.loldex.core.model.enums.ThemeConfig
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

    override val themeConfig: Flow<ThemeConfig> =
        userPreferencesDataSource.themeConfig

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        userPreferencesDataSource.setThemeConfig(themeConfig)
    }
}