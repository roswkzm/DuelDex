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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.model.yugioh.YugiohCardData
import com.example.loldex.feature.home.ui.DigimonContentItem

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val digimonListPagingItems: LazyPagingItems<YugiohCardData> =
        viewModel.yugiohListState.collectAsLazyPagingItems()
    val scrollState = rememberLazyGridState()

    HomeScreen(
        yugiohListPagingItems = digimonListPagingItems,
        scrollState = scrollState,
        onClickedContentItem = {}
    )
}

@Composable
internal fun HomeScreen(
    yugiohListPagingItems: LazyPagingItems<YugiohCardData>,
    scrollState: LazyGridState,
    onClickedContentItem: (Long) -> Unit
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
        items(yugiohListPagingItems.itemCount) { index ->
            yugiohListPagingItems[index]?.let { yugiohCardData ->
                DigimonContentItem(
                    onClickedItem = onClickedContentItem,
                    yugiohCardData = yugiohCardData,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun HomeScreenPreivew() {
}