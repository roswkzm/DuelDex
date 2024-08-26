package com.example.loldex.feature.decks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.ui.util.statusBarPadding

@Composable
internal fun DeckDetailRoute(
    deckData: DeckData,
    viewModel: DeckDetailViewModel = hiltViewModel()
) {
    val deckDetailUiState by viewModel.deckDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(deckData.id) {
        viewModel.getDeckWithCards(deckData.id)
    }

    DeckDetailScreen(
        deckDetailUiState = deckDetailUiState
    )
}

@Composable
internal fun DeckDetailScreen(
    deckDetailUiState: DeckDetailUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {

        when (deckDetailUiState) {
            DeckDetailUiState.Loading -> {

            }

            is DeckDetailUiState.Error -> {

            }

            is DeckDetailUiState.Success -> {
                val deckWithCardData = deckDetailUiState.deckWithCardData
                Text(text = "DeckDetailScreen")
                Text(text = "Deck Id -> ${deckWithCardData.deckData.id}")
                Text(text = "Deck Name -> ${deckWithCardData.deckData.deckName}")
                Text(text = "Deck Card Size -> ${deckWithCardData.yugiohCardList.size}")
            }
        }
    }
}