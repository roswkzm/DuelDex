package com.example.loldex.feature.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CardDetailRoute(
    cardId: Long,
    viewModel: CardDetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getYugiohCardDataById(cardId)
    }

    val cardDetailUiState by viewModel.cardDetailUiState.collectAsStateWithLifecycle()

    CardDetailScreen(
        cardId = cardId,
        cardDetailUiState = cardDetailUiState
    )
}

@Composable
internal fun CardDetailScreen(
    cardId: Long,
    cardDetailUiState: CardDetailUiState
) {
    Text(text = "CardDetailScreen -> $cardId")

    when (cardDetailUiState) {
        CardDetailUiState.Error -> {

        }

        CardDetailUiState.Loading -> {

        }

        is CardDetailUiState.Success -> {

        }
    }
}