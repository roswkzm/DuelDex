package com.example.dueldex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.dueldex.feature.decks.navigation.decksGraph
import com.example.dueldex.feature.decks.navigation.navigateToDeckDetail
import com.example.dueldex.feature.detail.navigation.detailGraph
import com.example.dueldex.feature.detail.navigation.navigateToCardDetail
import com.example.dueldex.feature.home.navigation.HOME_GRAPH_ROUTE
import com.example.dueldex.feature.home.navigation.homeGraph
import com.example.dueldex.feature.search.navigation.navigateToSearch
import com.example.dueldex.feature.search.navigation.searchScreen
import com.example.dueldex.ui.AppState

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
        decksGraph(
            onClickedDeck = navController::navigateToDeckDetail,
            onClickedCardItem = navController::navigateToCardDetail,
        )
        detailGraph()
        searchScreen(
            onClickedCardItem = navController::navigateToCardDetail,
            onClickedClose = navController::popBackStack
        )
    }
}