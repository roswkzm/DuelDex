package com.example.loldex.feature.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun CardDetailRoute(
    cardId: Long,
    viewModel: CardDetailViewModel = hiltViewModel(),
) {
    CardDetailScreen(
        cardId = cardId
    )
}

@Composable
internal fun CardDetailScreen(
    cardId: Long,
) {
    Text(text = "CardDetailScreen -> $cardId")
}