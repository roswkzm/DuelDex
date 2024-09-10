package com.example.dueldex.core.datastore

import androidx.datastore.core.DataStore
import com.example.dueldex.core.model.UserEnvData
import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    companion object {
        private const val MAX_RECENT_SEARCHES_SIZE = 10  // 최대 크기를 상수로 정의
    }

    val getRecentSearchList = userPreferences.data
        .map { preferences ->
            preferences.recentSearchesList
        }

    suspend fun addRecentSearch(searchText: String) {
        userPreferences.updateData { preferences ->
            val currentList = preferences.recentSearchesList
            // List에 있는 경우, 기존 preferences를 그대로 반환하여 불필요한 업데이트 방지
            if (currentList.contains(searchText)) {
                return@updateData preferences
            }
            val updatedList = currentList.toMutableList().apply {
                if (size >= MAX_RECENT_SEARCHES_SIZE) {
                    removeAt(0)
                }
                add(searchText)
            }
            preferences.toBuilder().clearRecentSearches().addAllRecentSearches(updatedList).build()
        }
    }

    suspend fun removeRecentSearch(searchText: String) {
        userPreferences.updateData { preferences ->
            val currentList = preferences.recentSearchesList
            // List에 없는 경우, 기존 preferences를 그대로 반환하여 불필요한 업데이트 방지
            if (!currentList.contains(searchText)) {
                return@updateData preferences
            }
            val updatedList = currentList.toMutableList().apply {
                remove(searchText)
            }
            preferences.toBuilder().clearRecentSearches().addAllRecentSearches(updatedList).build()
        }
    }

    suspend fun clearAllRecentSearches() {
        userPreferences.updateData { preferences ->
            preferences.toBuilder().clearRecentSearches().build()
        }
    }

    val userEnvData = userPreferences.data
        .map { preferences ->
            UserEnvData(
                themeConfig = when (preferences.themeConfig) {
                    null,
                    ThemeConfigProto.THEME_CONFIG_UNSPECIFIED,
                    ThemeConfigProto.UNRECOGNIZED,
                    ThemeConfigProto.THEME_CONFIG_FOLLOW_SYSTEM,
                    -> ThemeConfig.FOLLOW_SYSTEM

                    ThemeConfigProto.THEME_CONFIG_LIGHT -> ThemeConfig.LIGHT
                    ThemeConfigProto.THEME_CONFIG_DARK -> ThemeConfig.DARK
                },
                localizationConfig = when (preferences.localizationConfig) {
                    null,
                    LocalizationConfigProto.LOCALIZATION_CONFIG_UNSPECIFIED,
                    LocalizationConfigProto.UNRECOGNIZED,
                    LocalizationConfigProto.LOCALIZATION_CONFIG_FOLLOW_SYSTEM,
                    -> LocalizationConfig.FOLLOW_SYSTEM

                    LocalizationConfigProto.LOCALIZATION_CONFIG_ENGLISH -> LocalizationConfig.ENGLISH
                    LocalizationConfigProto.LOCALIZATION_CONFIG_KOREAN -> LocalizationConfig.KOREAN
                }
            )
        }

    suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        userPreferences.updateData {
            it.copy {
                this.themeConfig = when (themeConfig) {
                    ThemeConfig.FOLLOW_SYSTEM -> ThemeConfigProto.THEME_CONFIG_FOLLOW_SYSTEM
                    ThemeConfig.LIGHT -> ThemeConfigProto.THEME_CONFIG_LIGHT
                    ThemeConfig.DARK -> ThemeConfigProto.THEME_CONFIG_DARK
                }
            }
        }
    }

    suspend fun setLocalizationConfig(localizationConfig: LocalizationConfig) {
        userPreferences.updateData {
            it.copy {
                this.localizationConfig = when (localizationConfig) {
                    LocalizationConfig.FOLLOW_SYSTEM -> LocalizationConfigProto.LOCALIZATION_CONFIG_FOLLOW_SYSTEM
                    LocalizationConfig.ENGLISH -> LocalizationConfigProto.LOCALIZATION_CONFIG_ENGLISH
                    LocalizationConfig.KOREAN -> LocalizationConfigProto.LOCALIZATION_CONFIG_KOREAN
                }
            }
        }
    }
}