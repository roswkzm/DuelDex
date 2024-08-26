package com.example.loldex.feature.decks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loldex.core.model.DeckData

@Composable
internal fun DeckDetailRoute(
    deckData: DeckData,
    viewModel: DeckDetailViewModel = hiltViewModel()
) {
    DeckDetailScreen(
        deckData = deckData,
    )
}

@Composable
internal fun DeckDetailScreen(
    deckData: DeckData,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "DeckDetailScreen")
        Text(text = "Deck Id -> ${deckData.id}")
        Text(text = "Deck Name -> ${deckData.deckName}")
    }
}