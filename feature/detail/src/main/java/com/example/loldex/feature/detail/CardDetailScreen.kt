package com.example.loldex.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CardDetailRoute(
    viewModel: CardDetailViewModel = hiltViewModel(),
) {
    val cardDetailUiState by viewModel.cardDetailUiState.collectAsStateWithLifecycle()

    CardDetailScreen(
        cardDetailUiState = cardDetailUiState
    )
}

@Composable
internal fun CardDetailScreen(
    cardDetailUiState: CardDetailUiState
) {
    when (cardDetailUiState) {
        CardDetailUiState.Error -> {

        }

        CardDetailUiState.Loading -> {

        }

        is CardDetailUiState.Success -> {

        }
    }
}