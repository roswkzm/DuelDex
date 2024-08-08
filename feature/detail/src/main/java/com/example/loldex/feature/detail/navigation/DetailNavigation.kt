package com.example.loldex.feature.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.loldex.feature.detail.CardDetailRoute

const val DETAIL_CARD_ID_ARG = "card_id"

const val DETAIL_GRAPH_ROUTE = "detail_graph"
const val DETAIL_ROUTE = "detail"

fun NavController.navigateToCardDetail(cardId: Long) {
    navigate("$DETAIL_ROUTE/$cardId")
}

fun NavGraphBuilder.detailGraph() {
    navigation(
        route = DETAIL_GRAPH_ROUTE,
        startDestination = DETAIL_ROUTE
    ) {
        composable(
            route = "$DETAIL_ROUTE/{$DETAIL_CARD_ID_ARG}",
            arguments = listOf(
                navArgument(DETAIL_CARD_ID_ARG) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getLong(DETAIL_CARD_ID_ARG)
            cardId?.let {
                CardDetailRoute(cardId = it)
            }
        }
    }
}