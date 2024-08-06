package com.example.loldex.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.model.DigimonContentData
import com.example.loldex.feature.home.ui.DigimonContentItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val digimonListPagingItems: LazyPagingItems<DigimonContentData> =
        viewModel.digimonListState.collectAsLazyPagingItems()
    val scrollState = rememberLazyGridState()

    HomeScreen(
        digimonListPagingItems = digimonListPagingItems,
        scrollState = scrollState,
        onClickedContentItem = {}
    )
}

@Composable
internal fun HomeScreen(
    digimonListPagingItems: LazyPagingItems<DigimonContentData>,
    scrollState: LazyGridState,
    onClickedContentItem: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = scrollState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(digimonListPagingItems.itemCount) { index ->
            digimonListPagingItems[index]?.let { digimonContentData ->
                DigimonContentItem(
                    onClickedItem = onClickedContentItem,
                    digimonContentData = digimonContentData,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun HomeScreenPreivew() {
    val sampleData = listOf(
        DigimonContentData(id = 1, name = "Agumon", image = "", href = ""),
        DigimonContentData(id = 2, name = "Gabumon", image = "", href = ""),
        DigimonContentData(id = 3, name = "Piyomon", image = "", href = ""),
        DigimonContentData(id = 4, name = "Tentomon", image = "", href = ""),
    )

    val fakePagingDataFlow: Flow<PagingData<DigimonContentData>> =
        flowOf(PagingData.from(sampleData))
    val digimonListPagingItems = fakePagingDataFlow.collectAsLazyPagingItems()

    val scrollState = rememberLazyGridState()

    LolDexTheme {
        HomeScreen(
            digimonListPagingItems = digimonListPagingItems,
            scrollState = scrollState,
            onClickedContentItem = {}
        )
    }
}