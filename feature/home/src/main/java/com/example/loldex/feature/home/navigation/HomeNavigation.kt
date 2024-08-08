package com.example.loldex.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.loldex.feature.home.HomeRoute

const val HOME_GRAPH_ROUTE = "home_graph"
const val HOME_ROUTE = "home"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_GRAPH_ROUTE, navOptions)

fun NavGraphBuilder.homeGraph(
    onClickedCardItem: (Long) -> Unit,
) {
    navigation(
        route = HOME_GRAPH_ROUTE,
        startDestination = HOME_ROUTE
    ) {
        composable(route = HOME_ROUTE) {
            HomeRoute(
                onClickedCardItem = onClickedCardItem
            )
        }
    }
}