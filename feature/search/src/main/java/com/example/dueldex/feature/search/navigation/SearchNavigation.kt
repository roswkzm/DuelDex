package com.example.dueldex.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dueldex.feature.search.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearch() = navigate(SEARCH_ROUTE)

fun NavGraphBuilder.searchScreen(
    onClickedCardItem: (String) -> Unit,
    onClickedClose: () -> Unit,
) {
    composable(
        route = SEARCH_ROUTE
    ) {
        SearchRoute(
            onClickedCardItem = onClickedCardItem,
            onClickedClose = onClickedClose,
        )
    }
}