package com.example.loldex.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.loldex.core.designsystem.theme.Neutral10
import com.example.loldex.core.designsystem.theme.Neutral30
import com.example.loldex.core.designsystem.theme.ThemePreviews
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebouncingSearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    color: Color,
    content: @Composable () -> Unit,
) {
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()

    SearchBar(
        modifier = modifier,
        query = searchQuery,
        onQueryChange = { query ->
            onQueryChange(query)
            debounceJob?.cancel()
            debounceJob = scope.launch {
                delay(500)
                onSearch(query)
            }
        },
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        colors = SearchBarDefaults.colors(
            containerColor = color,
            dividerColor = Neutral30,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    ) {
        content()
    }
}

@ThemePreviews
@Composable
fun DebouncingSearchBarPreview() {
    var searchQuery by remember { mutableStateOf("SearchQuery") }
    var isSearchBarActive by remember { mutableStateOf(false) }
    DebouncingSearchBar(
        modifier = Modifier.fillMaxWidth(),
        searchQuery = searchQuery,
        onQueryChange = { query ->
            searchQuery = query
        },
        active = isSearchBarActive,
        onActiveChange = { isActive ->
            isSearchBarActive = isActive
        },
        onSearch = {},
        color = Neutral10
    ) {

    }
}