package com.example.loldex.core.data.repository

import com.example.loldex.core.datastore.RecentSearchPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RecentSearchRepositoryImpl @Inject constructor(
    private val recentSearchPreferencesDataSource: RecentSearchPreferencesDataSource,
) : RecentSearchRepository {

    override val recentSearchList: Flow<List<String>> =
        recentSearchPreferencesDataSource.getRecentSearchList

    override suspend fun addRecentSearch(searchText: String) {
        recentSearchPreferencesDataSource.addRecentSearch(searchText)
    }

    override suspend fun removeRecentSearch(searchText: String) {
        recentSearchPreferencesDataSource.removeRecentSearch(searchText)
    }

    override suspend fun clearAllRecentSearches() {
        recentSearchPreferencesDataSource.clearAllRecentSearches()
    }


}