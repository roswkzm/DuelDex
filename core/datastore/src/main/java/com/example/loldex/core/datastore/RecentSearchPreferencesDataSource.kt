package com.example.loldex.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchPreferencesDataSource @Inject constructor(
    private val recentSearchPreferences: DataStore<RecentSearchPreferences>
) {

    val getRecentSearchList = recentSearchPreferences.data
        .map { preferences ->
            preferences.recentSearchesList
        }

    suspend fun addRecentSearch(searchText: String) {
        recentSearchPreferences.updateData { preferences ->
            val currentList = preferences.recentSearchesList
            // List에 있는 경우, 기존 preferences를 그대로 반환하여 불필요한 업데이트 방지
            if (currentList.contains(searchText)) {
                return@updateData preferences
            }
            val updatedList = currentList.toMutableList().apply {
                add(searchText)
            }
            preferences.toBuilder().clearRecentSearches().addAllRecentSearches(updatedList).build()
        }
    }

    suspend fun removeRecentSearch(searchText: String) {
        recentSearchPreferences.updateData { preferences ->
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
        recentSearchPreferences.updateData { preferences ->
            preferences.toBuilder().clearRecentSearches().build()
        }
    }
}