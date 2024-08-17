package com.example.loldex.core.data.repository

import com.example.loldex.core.datastore.RecentSearchPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RecentSearchRepositoryImpl @Inject constructor(
    private val recentSearchPreferencesDataSource: RecentSearchPreferencesDataSource,
) : RecentSearchRepository {

    override val recentSearchList: Flow<List<String>> =
        recentSearchPreferencesDataSource.getRecentSearchList.map { it.reversed() }

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