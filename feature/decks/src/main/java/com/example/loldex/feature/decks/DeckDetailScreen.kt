package com.example.loldex.feature.decks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun DeckDetailRoute(
    viewModel: DeckDetailViewModel = hiltViewModel()
) {
    DeckDetailScreen()
}

@Composable
internal fun DeckDetailScreen(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "DeckDetailScreen")
    }
}