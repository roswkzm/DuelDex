package com.example.loldex.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.theme.Neutral10
import com.example.loldex.core.designsystem.theme.Text20
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.GridYugiohCardItem
import com.example.loldex.core.ui.preview_parameter_provider.YugiohCardDataPreviewParameterProvider
import com.example.loldex.core.ui.util.statusBarPadding
import com.example.loldex.feature.search.ui.RecentSearches
import com.example.loldex.feature.search.ui.RecommendedKeywords
import com.example.loldex.feature.search.ui.SearchTextField
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onClickedCardItem: (String) -> Unit,
    onClickedClose: () -> Unit,
) {
    val recentSearchList by viewModel.recentSearchList.collectAsStateWithLifecycle(initialValue = emptyList())
    val cardSearchResult by viewModel.cardSearchResult.collectAsStateWithLifecycle()
    val scrollState = rememberLazyGridState()

    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()

    var searchValue by remember { mutableStateOf("") }
    val recommendedKeywordList = listOf("Dark Magician")

    LaunchedEffect(searchValue) {
        if (searchValue.isEmpty()) {
            viewModel.cardSearchStateIdle()
        } else {
            debounceJob?.cancel()
            debounceJob = scope.launch {
                delay(500)
                viewModel.cardSearchToQuery(searchValue)
            }
        }
    }

    SearchScreen(
        cardSearchResult = cardSearchResult,
        scrollState = scrollState,
        searchValue = searchValue,
        onSearchValueChange = { searchValue = it },
        onSearch = {
            viewModel.addRecentSearch(it)
            viewModel.cardSearchToQuery(it)
        },
        recentSearchList = recentSearchList,
        recommendedKeywordList = recommendedKeywordList,
        onClickedTag = { searchValue = it },
        onClickedDeleteTag = viewModel::removeRecentSearch,
        onClickedDeleteAll = viewModel::clearAllRecentSearches,
        onClickedCardItem = onClickedCardItem,
        onClickedDeleteString = { searchValue = "" },
        onClickedClose = onClickedClose
    )
}

@Composable
internal fun SearchScreen(
    cardSearchResult: SearchResultUiState,
    scrollState: LazyGridState,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    recentSearchList: List<String>,
    recommendedKeywordList: List<String>,
    onClickedTag: (String) -> Unit,
    onClickedDeleteTag: (String) -> Unit,
    onClickedDeleteAll: () -> Unit,
    onClickedCardItem: (String) -> Unit,
    onClickedDeleteString: () -> Unit,
    onClickedClose: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral10)
            .statusBarPadding()
    ) {
        SearchTextField(
            searchValue = searchValue,
            onSearchValueChange = onSearchValueChange,
            onSearch = onSearch,
            onClickedDeleteString = onClickedDeleteString,
            onClickedClose = onClickedClose,
        )

        Column {
            if (recentSearchList.isNotEmpty()) {
                RecentSearches(
                    recentSearchList = recentSearchList,
                    onClickedTag = onClickedTag,
                    onClickedDeleteTag = onClickedDeleteTag,
                    onClickedDeleteAll = onClickedDeleteAll,
                )
            }

            if (recommendedKeywordList.isNotEmpty()) {
                RecommendedKeywords(
                    recommendedKeywordList = recommendedKeywordList,
                    onClickedTag = onClickedTag
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(start = 6.dp, top = 10.dp, end = 6.dp),
                thickness = 1.dp,
                color = Text20
            )

            when (cardSearchResult) {
                SearchResultUiState.Idle -> {}

                SearchResultUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier)
                    }
                }

                is SearchResultUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(90.dp),
                            imageVector = Icons.Filled.ErrorOutline,
                            contentDescription = "Error Icon",
                            tint = Color.Red,
                        )
                        Text(
                            text = cardSearchResult.errorMessage,
                            style = MaterialTheme.ldTypography.fontTitleM,
                            color = Color.Red
                        )
                    }
                }

                is SearchResultUiState.Success -> {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 12.dp, end = 10.dp),
                        columns = GridCells.Fixed(2),
                        state = scrollState,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        val yugiohCardList = cardSearchResult.yugiohCardList
                        items(
                            key = { index -> yugiohCardList[index].id },
                            count = yugiohCardList.size
                        ) { index ->
                            GridYugiohCardItem(
                                onClickedItem = onClickedCardItem,
                                yugiohCardData = yugiohCardList[index],
                            )
                        }
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun SearchScreenPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    val scrollState = rememberLazyGridState()
    var searchValue by remember { mutableStateOf("") }
    val recentSearchList = listOf("가나다", "라마바", "사아자", "차카타", "파하")
    val recommendedKeywordList = listOf("Dark Magician")
    SearchScreen(
        cardSearchResult = SearchResultUiState.Success(yugiohCardList),
        scrollState = scrollState,
        searchValue = searchValue,
        onSearchValueChange = { searchValue = it },
        onSearch = {},
        recentSearchList = recentSearchList,
        recommendedKeywordList = recommendedKeywordList,
        onClickedTag = {},
        onClickedDeleteTag = {},
        onClickedDeleteAll = {},
        onClickedCardItem = {},
        onClickedDeleteString = {},
        onClickedClose = {},
    )
}