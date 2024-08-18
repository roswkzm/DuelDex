package com.example.loldex.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.loldex.R

enum class TopLevelDestination(
    val selectIcon: ImageVector,
    val unselectIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectIcon = Icons.Rounded.Home,
        unselectIcon = Icons.Filled.Home,
        iconTextId = R.string.feature_home,
        titleTextId = R.string.app_name
    ),
    DECKS(
        selectIcon = Icons.Rounded.Bookmarks,
        unselectIcon = Icons.Filled.Bookmarks,
        iconTextId = R.string.feature_decks,
        titleTextId = R.string.feature_decks
    )
}