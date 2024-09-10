package com.example.dueldex.core.data.repository

import com.example.dueldex.core.model.UserEnvData
import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val recentSearchList: Flow<List<String>>

    suspend fun addRecentSearch(searchText: String)

    suspend fun removeRecentSearch(searchText: String)

    suspend fun clearAllRecentSearches()

    val userEnvData: Flow<UserEnvData>

    suspend fun setThemeConfig(themeConfig: ThemeConfig)

    suspend fun setLocalizationConfig(localizationConfig: LocalizationConfig)
}