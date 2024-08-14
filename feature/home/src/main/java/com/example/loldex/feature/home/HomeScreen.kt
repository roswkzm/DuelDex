package com.example.loldex.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.loldex.core.designsystem.theme.Neutral10
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.DebouncingSearchBar
import com.example.loldex.core.ui.YugiohCardDataPreviewParameterProvider
import com.example.loldex.core.ui.YugiohCardItem
import com.example.loldex.core.ui.pagingLoadStateHandler
import com.example.loldex.feature.home.ui.component.LoadStateAppendError
import com.example.loldex.feature.home.ui.component.LoadStateAppendSkeletonLoading
import com.example.loldex.feature.home.ui.component.LoadStateRefreshError
import com.example.loldex.feature.home.ui.component.LoadStateRefreshSkeletonLoading
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickedCardItem: (String) -> Unit,
) {
    val yugiohListPagingItems: LazyPagingItems<YugiohCardData> =
        viewModel.yugiohListState.collectAsLazyPagingItems()
    val cardSearchResult by viewModel.cardSearchResult.collectAsStateWithLifecycle()
    val searchScrollState = rememberLazyGridState()
    val pagingScrollState = rememberLazyGridState()
    var hasAppendErrorShown = remember { mutableStateOf(false) }

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isSearchBarActive by rememberSaveable { mutableStateOf(false) }

    HomeScreen(
        cardSearchResult = cardSearchResult,
        yugiohListPagingItems = yugiohListPagingItems,
        pagingScrollState = pagingScrollState,
        onClickedCardItem = onClickedCardItem,
        hasAppendErrorShown = hasAppendErrorShown,
        searchScrollState = searchScrollState,
        searchQuery = searchQuery,
        onQueryChange = { searchQuery = it },
        isSearchBarActive = isSearchBarActive,
        onActiveChange = { isSearchBarActive = it },
        onSearch = {
            if (searchQuery.isEmpty()) {
                viewModel.cardSearchStateIdle()
            } else {
                viewModel.cardSearchToQuery(searchQuery)
            }
        }
    )
}

@Composable
internal fun HomeScreen(
    cardSearchResult: SearchResultUiState,
    yugiohListPagingItems: LazyPagingItems<YugiohCardData>,
    pagingScrollState: LazyGridState,
    onClickedCardItem: (String) -> Unit,
    hasAppendErrorShown: MutableState<Boolean>,
    searchScrollState: LazyGridState,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    isSearchBarActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DebouncingSearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            searchQuery = searchQuery,
            onQueryChange = onQueryChange,
            active = isSearchBarActive,
            onActiveChange = onActiveChange,
            onSearch = onSearch,
            color = Neutral10
        ) {
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
                            .padding(horizontal = 10.dp),
                        columns = GridCells.Fixed(2),
                        state = searchScrollState,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        val yugiohCardList = cardSearchResult.yugiohCardList
                        items(yugiohCardList.size) { index ->
                            YugiohCardItem(
                                onClickedItem = onClickedCardItem,
                                yugiohCardData = yugiohCardList[index],
                            )
                        }
                    }
                }
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            columns = GridCells.Fixed(2),
            state = pagingScrollState,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(yugiohListPagingItems.itemCount) { index ->
                yugiohListPagingItems[index]?.let { yugiohCardData ->
                    YugiohCardItem(
                        onClickedItem = onClickedCardItem,
                        yugiohCardData = yugiohCardData,
                    )
                }
            }
            handlePagingLoadState(
                yugiohListPagingItems = yugiohListPagingItems,
                hasAppendErrorShown = hasAppendErrorShown
            )
        }
    }
}

private fun LazyGridScope.handlePagingLoadState(
    yugiohListPagingItems: LazyPagingItems<YugiohCardData>,
    hasAppendErrorShown: MutableState<Boolean>
) {
    pagingLoadStateHandler(
        pagingItems = yugiohListPagingItems,
        loadStateRefreshLoading = {
            this@handlePagingLoadState.LoadStateRefreshSkeletonLoading()
        },
        loadStateRefreshError = { error ->
            this@handlePagingLoadState.LoadStateRefreshError(
                error = error,
                onClickRetry = {
                    yugiohListPagingItems.retry()
                }
            )

        },
        loadStateAppendLoading = {
            this@handlePagingLoadState.LoadStateAppendSkeletonLoading()
        },
        loadStateAppendError = { error ->
            if (!hasAppendErrorShown.value) {
                hasAppendErrorShown.value = true
                this@handlePagingLoadState.LoadStateAppendError(
                    error = error,
                    onClickRetry = {
                        hasAppendErrorShown.value = false
                        yugiohListPagingItems.retry()
                    }
                )
            }
        }
    )
}

@ThemePreviews
@Composable
fun HomeScreenPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    val yugiohPagingData = PagingData.from(yugiohCardList)
    val yugiohPagingItems = flowOf(yugiohPagingData).collectAsLazyPagingItems()
    val searchScrollState = rememberLazyGridState()
    val pagingScrollState = rememberLazyGridState()
    val hasAppendErrorShown = remember { mutableStateOf(false) }

    var searchQuery by remember { mutableStateOf("") }
    var isSearchBarActive by remember { mutableStateOf(false) }

    HomeScreen(
        cardSearchResult = SearchResultUiState.Success(yugiohCardList),
        yugiohListPagingItems = yugiohPagingItems,
        pagingScrollState = pagingScrollState,
        onClickedCardItem = {},
        hasAppendErrorShown = hasAppendErrorShown,
        searchScrollState = searchScrollState,
        searchQuery = searchQuery,
        onQueryChange = {},
        isSearchBarActive = isSearchBarActive,
        onActiveChange = {},
        onSearch = {}
    )
}