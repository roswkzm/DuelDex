package com.example.loldex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.loldex.feature.decks.navigation.decksScreen
import com.example.loldex.feature.detail.navigation.detailGraph
import com.example.loldex.feature.detail.navigation.navigateToCardDetail
import com.example.loldex.feature.home.navigation.HOME_GRAPH_ROUTE
import com.example.loldex.feature.home.navigation.homeGraph
import com.example.loldex.feature.search.navigation.navigateToSearch
import com.example.loldex.feature.search.navigation.searchScreen
import com.example.loldex.ui.AppState

@Composable
fun LdNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_GRAPH_ROUTE
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        homeGraph(
            onClickedCardItem = navController::navigateToCardDetail,
            onClickedSearchIcon = navController::navigateToSearch,
        )
        decksScreen()
        detailGraph()
        searchScreen(
            onClickedCardItem = navController::navigateToCardDetail,
            onClickedClose = navController::popBackStack
        )
    }
}