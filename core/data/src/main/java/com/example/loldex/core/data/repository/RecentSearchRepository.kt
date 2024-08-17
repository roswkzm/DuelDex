package com.example.loldex.core.data.repository

import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {

    val recentSearchList: Flow<List<String>>

    suspend fun addRecentSearch(searchText: String)

    suspend fun removeRecentSearch(searchText: String)

    suspend fun clearAllRecentSearches()
}