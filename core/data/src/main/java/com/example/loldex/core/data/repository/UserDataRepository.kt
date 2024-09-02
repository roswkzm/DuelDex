package com.example.loldex.core.data.repository

import com.example.loldex.core.model.enums.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val recentSearchList: Flow<List<String>>

    suspend fun addRecentSearch(searchText: String)

    suspend fun removeRecentSearch(searchText: String)

    suspend fun clearAllRecentSearches()

    val themeConfig: Flow<ThemeConfig>

    suspend fun setThemeConfig(themeConfig: ThemeConfig)
}