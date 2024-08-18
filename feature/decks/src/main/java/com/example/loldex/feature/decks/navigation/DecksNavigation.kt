package com.example.loldex.feature.decks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.loldex.feature.decks.DecksRoute

const val DECKS_ROUTE = "decks"

fun NavController.navigateToDecks(navOptions: NavOptions) = navigate(DECKS_ROUTE, navOptions)

fun NavGraphBuilder.decksScreen() {
    composable(
        route = DECKS_ROUTE
    ) {
        DecksRoute()
    }
}