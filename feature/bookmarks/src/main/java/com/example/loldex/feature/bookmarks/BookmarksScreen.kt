package com.example.loldex.feature.bookmarks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun BookmarksRoute(
    viewModel: BookmarksViewModel = hiltViewModel()
) {
    BookmarksScreen()
}

@Composable
internal fun BookmarksScreen(

) {
    Text(text = "Bookmarks Screen")
}