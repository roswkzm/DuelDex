package com.example.dueldex.feature.decks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.dueldex.core.model.DeckData
import com.example.dueldex.feature.decks.DeckDetailRoute
import com.example.dueldex.feature.decks.DecksRoute

const val DECKS_GRAPH_ROUTE = "decks_graph"
const val DECKS_ROUTE = "decks"

fun NavController.navigateToDecks(navOptions: NavOptions) = navigate(DECKS_ROUTE, navOptions)

fun NavController.navigateToDeckDetail(deckData: DeckData) = navigate(deckData)

fun NavGraphBuilder.decksGraph(
    onClickedDeck: (DeckData) -> Unit,
    onClickedCardItem: (String) -> Unit,
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

        composable<DeckData> { backStackEntry ->
            val deckData: DeckData = backStackEntry.toRoute()
            DeckDetailRoute(
                deckData = deckData,
                onClickedCardItem = onClickedCardItem
            )
        }
    }
}