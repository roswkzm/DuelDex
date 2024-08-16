package com.example.loldex.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.loldex.feature.search.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearch() = navigate(SEARCH_ROUTE)

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_ROUTE
    ) {
        SearchRoute()
    }
}