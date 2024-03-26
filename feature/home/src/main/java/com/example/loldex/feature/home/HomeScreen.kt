package com.example.loldex.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen()
}

@Composable
internal fun HomeScreen(

) {
    Text(text = "Home Screen")
}