package com.example.loldex.feature.decks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.loldex.core.model.DeckData
import com.example.loldex.feature.decks.DecksRoute

const val DECKS_GRAPH_ROUTE = "decks_graph"
const val DECKS_ROUTE = "decks"
const val DECK_DETAIL_ROUTE = "deck_detail"

fun NavController.navigateToDecks(navOptions: NavOptions) = navigate(DECKS_ROUTE, navOptions)

fun NavGraphBuilder.decksGraph(
    onClickedDeck: (DeckData) -> Unit,
) {
    navigation(
        route = DECKS_GRAPH_ROUTE,
        startDestination = DECKS_ROUTE
    ) {
        composable(route = DECKS_ROUTE) {
            DecksRoute(
                onClickedDeck = onClickedDeck
            )
        }
    }
}