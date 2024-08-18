package com.example.loldex.feature.decks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loldex.core.ui.util.statusBarPadding

@Composable
internal fun DecksRoute(
    viewModel: DecksViewModel = hiltViewModel()
) {
    DecksScreen()
}

@Composable
internal fun DecksScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {
        Text(text = "Decks Screen")
    }
}