package com.example.loldex.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.Text0
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.YugiohCardDataPreviewParameterProvider
import com.example.loldex.core.ui.YugiohCardItem
import com.example.loldex.core.ui.pagingLoadStateHandler
import com.example.loldex.feature.home.ui.component.LoadStateAppendError
import com.example.loldex.feature.home.ui.component.LoadStateAppendSkeletonLoading
import com.example.loldex.feature.home.ui.component.LoadStateRefreshError
import com.example.loldex.feature.home.ui.component.LoadStateRefreshSkeletonLoading
import kotlinx.coroutines.flow.flowOf
import com.example.loldex.core.designsystem.R as DesignR

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickedCardItem: (String) -> Unit,
    onClickedSearchIcon: () -> Unit,
) {
    val yugiohListPagingItems: LazyPagingItems<YugiohCardData> =
        viewModel.yugiohListState.collectAsLazyPagingItems()
    val pagingScrollState = rememberLazyGridState()
    var hasAppendErrorShown = remember { mutableStateOf(false) }

    HomeScreen(
        yugiohListPagingItems = yugiohListPagingItems,
        pagingScrollState = pagingScrollState,
        onClickedCardItem = onClickedCardItem,
        hasAppendErrorShown = hasAppendErrorShown,
        onClickedSearchIcon = onClickedSearchIcon,
    )
}

@Composable
internal fun HomeScreen(
    yugiohListPagingItems: LazyPagingItems<YugiohCardData>,
    pagingScrollState: LazyGridState,
    onClickedCardItem: (String) -> Unit,
    hasAppendErrorShown: MutableState<Boolean>,
    onClickedSearchIcon: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HomeTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 10.dp, end = 20.dp),
            onClickedSearchIcon = onClickedSearchIcon,
        )

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

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onClickedSearchIcon: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(),
            painter = painterResource(id = DesignR.drawable.yugioh_logo),
            contentDescription = "Logo Image",
        )
        Icon(
            modifier = Modifier
                .size(28.dp)
                .clickable { onClickedSearchIcon() },
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            tint = Text0
        )
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
    val pagingScrollState = rememberLazyGridState()
    val hasAppendErrorShown = remember { mutableStateOf(false) }

    LolDexTheme {
        LdBackGround {
            HomeScreen(
                yugiohListPagingItems = yugiohPagingItems,
                pagingScrollState = pagingScrollState,
                onClickedCardItem = {},
                hasAppendErrorShown = hasAppendErrorShown,
                onClickedSearchIcon = {},
            )
        }
    }
}