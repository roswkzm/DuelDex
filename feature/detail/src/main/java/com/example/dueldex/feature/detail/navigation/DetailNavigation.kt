package com.example.dueldex.feature.detail.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.dueldex.feature.detail.CardDetailRoute
import java.net.URLDecoder

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

const val DETAIL_GRAPH_ROUTE = "detail_graph"
const val DETAIL_ROUTE = "detail"

const val DETAIL_CARD_NAME_ARG = "card_name"

internal class DetailArgs(
    val cardName: String,
) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[DETAIL_CARD_NAME_ARG]),
                    URL_CHARACTER_ENCODING
                )
            )
}

fun NavController.navigateToCardDetail(cardName: String) {
    navigate("$DETAIL_ROUTE/$cardName")
}

fun NavGraphBuilder.detailGraph() {
    navigation(
        route = DETAIL_GRAPH_ROUTE,
        startDestination = DETAIL_ROUTE
    ) {
        composable(
            route = "$DETAIL_ROUTE/{$DETAIL_CARD_NAME_ARG}",
            arguments = listOf(
                navArgument(DETAIL_CARD_NAME_ARG) { type = NavType.StringType }
            )
        ) {
            CardDetailRoute()
        }
    }
}