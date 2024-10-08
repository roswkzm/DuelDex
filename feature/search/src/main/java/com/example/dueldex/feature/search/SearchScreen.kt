package com.example.dueldex.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.ui.GridYugiohCardItem
import com.example.dueldex.core.ui.UiStateFailedScreen
import com.example.dueldex.core.ui.preview_parameter_provider.YugiohCardDataPreviewParameterProvider
import com.example.dueldex.core.ui.util.statusBarPadding
import com.example.dueldex.feature.search.ui.RecentSearches
import com.example.dueldex.feature.search.ui.RecommendedKeywords
import com.example.dueldex.feature.search.ui.SearchFilterType
import com.example.dueldex.feature.search.ui.SearchFilterType.ATTRIBUTE
import com.example.dueldex.feature.search.ui.SearchFilterType.CARD_TYPE
import com.example.dueldex.feature.search.ui.SearchFilterType.EFFECT
import com.example.dueldex.feature.search.ui.SearchFilterType.RACE
import com.example.dueldex.feature.search.ui.SearchFilters
import com.example.dueldex.feature.search.ui.SearchTextField
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
    val selectedFilterValues = remember { mutableStateMapOf<SearchFilterType, String?>() }
    var levelValue by remember { mutableStateOf(0f) }
    val recommendedKeywordList = listOf("Dark Magician")

    fun performSearch() {
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(500)
            cardSearchQueryToFilter(
                searchValue = searchValue,
                selectedFilterValues = selectedFilterValues,
                levelValue = levelValue,
                viewModel = viewModel
            )
        }
    }

    LaunchedEffect(searchValue, selectedFilterValues.toMap(), levelValue) {
        // 모든 Filters가 꺼져있는지 판단하는 Boolean ( Filter가 하나라도 살아있으면 해당 Filter로 검색이 되어야한다.)
        val areAllFiltersEmpty = selectedFilterValues.values.all { it.isNullOrEmpty() }
                && levelValue == 0f

        if (searchValue.isEmpty() && areAllFiltersEmpty) {
            debounceJob?.cancel()
            viewModel.cardSearchStateIdle()
        } else {
            performSearch()
        }
    }

    SearchScreen(
        cardSearchResult = cardSearchResult,
        scrollState = scrollState,
        searchValue = searchValue,
        onSearchValueChange = { searchValue = it },
        onSearch = {
            if (it.isNotEmpty()) {
                viewModel.addRecentSearch(it)
            }
            performSearch()
        },
        selectedFilterValues = selectedFilterValues,
        onFilterValueChange = { selectedFilterValues[it.first] = it.second },
        levelValue = levelValue,
        onLevelValueChange = { levelValue = it },
        recentSearchList = recentSearchList,
        recommendedKeywordList = recommendedKeywordList,
        onClickedTag = { searchValue = it },
        onClickedDeleteTag = viewModel::removeRecentSearch,
        onClickedDeleteAll = viewModel::clearAllRecentSearches,
        onClickedCardItem = onClickedCardItem,
        onClickedDeleteString = { searchValue = "" },
        onClickedClose = onClickedClose,
        onClickedRetry = {
            performSearch()
        }
    )
}

@Composable
internal fun SearchScreen(
    cardSearchResult: SearchResultUiState,
    scrollState: LazyGridState,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    selectedFilterValues: MutableMap<SearchFilterType, String?>,
    onFilterValueChange: (Pair<SearchFilterType, String?>) -> Unit,
    levelValue: Float,
    onLevelValueChange: (Float) -> Unit,
    recentSearchList: List<String>,
    recommendedKeywordList: List<String>,
    onClickedTag: (String) -> Unit,
    onClickedDeleteTag: (String) -> Unit,
    onClickedDeleteAll: () -> Unit,
    onClickedCardItem: (String) -> Unit,
    onClickedDeleteString: () -> Unit,
    onClickedClose: () -> Unit,
    onClickedRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
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
            SearchFilters(
                selectedFilterValues = selectedFilterValues,
                onFilterValueChange = onFilterValueChange,
                levelValue = levelValue,
                onLevelValueChange = onLevelValueChange
            )

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
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            when (cardSearchResult) {
                SearchResultUiState.Idle -> {}

                SearchResultUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                is SearchResultUiState.Error -> {
                    UiStateFailedScreen(
                        onClickedRetry = onClickedRetry
                    )
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

fun cardSearchQueryToFilter(
    searchValue: String,
    selectedFilterValues: MutableMap<SearchFilterType, String?>,
    levelValue: Float,
    viewModel: SearchViewModel,
) {
    val levelValueToPass: Int? = if (levelValue == 0f) null else levelValue.toInt()

    viewModel.cardSearchToQuery(
        searchQuery = searchValue,
        type = selectedFilterValues[CARD_TYPE],
        attribute = selectedFilterValues[ATTRIBUTE],
        race = selectedFilterValues[RACE],
        effect = selectedFilterValues[EFFECT],
        level = levelValueToPass
    )
}

fun setFilterTagText(searchFilterType: SearchFilterType): Int {
    return when (searchFilterType) {
        CARD_TYPE -> R.string.tag_filter_title_type
        ATTRIBUTE -> R.string.tag_filter_title_attribute
        RACE -> R.string.tag_filter_title_race
        EFFECT -> R.string.tag_filter_title_effect
    }
}

@ThemePreviews
@Composable
fun SearchScreenPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    DuelDexTheme {
        val scrollState = rememberLazyGridState()
        var searchValue by remember { mutableStateOf("") }
        val selectedFilterValues = remember { mutableStateMapOf<SearchFilterType, String?>() }
        var levelValue by remember { mutableStateOf(0f) }
        val recentSearchList = listOf("가나다", "라마바", "사아자", "차카타", "파하")
        val recommendedKeywordList = listOf("Dark Magician")
        SearchScreen(
            cardSearchResult = SearchResultUiState.Success(yugiohCardList),
            scrollState = scrollState,
            searchValue = searchValue,
            onSearchValueChange = { searchValue = it },
            onSearch = {},
            selectedFilterValues = selectedFilterValues,
            onFilterValueChange = {},
            levelValue = levelValue,
            onLevelValueChange = {},
            recentSearchList = recentSearchList,
            recommendedKeywordList = recommendedKeywordList,
            onClickedTag = {},
            onClickedDeleteTag = {},
            onClickedDeleteAll = {},
            onClickedCardItem = {},
            onClickedDeleteString = {},
            onClickedClose = {},
            onClickedRetry = {},
        )
    }
}