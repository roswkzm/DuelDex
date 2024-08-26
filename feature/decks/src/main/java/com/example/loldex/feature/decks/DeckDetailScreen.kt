package com.example.loldex.feature.decks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.theme.Gray900
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.model.DeckWithCardData
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.GridYugiohCardItem
import com.example.loldex.core.ui.ListYugiohCardItem
import com.example.loldex.core.ui.preview_parameter_provider.DeckWithCardDataPreviewParameterProvider
import com.example.loldex.core.ui.util.statusBarPadding

@Composable
internal fun DeckDetailRoute(
    deckData: DeckData,
    viewModel: DeckDetailViewModel = hiltViewModel()
) {
    val deckDetailUiState by viewModel.deckDetailUiState.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(deckData.id) {
        viewModel.getDeckWithCards(deckData.id)
    }

    DeckDetailScreen(
        deckDetailUiState = deckDetailUiState,
        lazyGridState = lazyGridState,
    )
}

@Composable
internal fun DeckDetailScreen(
    deckDetailUiState: DeckDetailUiState,
    lazyGridState: LazyGridState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {

        when (deckDetailUiState) {
            DeckDetailUiState.Loading -> {

            }

            is DeckDetailUiState.Error -> {

            }

            is DeckDetailUiState.Success -> {
                val deckData = deckDetailUiState.deckWithCardData.deckData
                val cardList = deckDetailUiState.deckWithCardData.yugiohCardList

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    DeckNameTitleLayout(
                        deckData = deckData,
                        onClickedDeckNameEdit = {}
                    )

//                    YugiohCardGridList(
//                        cardList = cardList,
//                        lazyGridState = lazyGridState
//                    )

                    YugiohCardList(
                        cardList = cardList,
                    )
                }
            }
        }
    }
}

@Composable
fun DeckNameTitleLayout(
    deckData: DeckData,
    onClickedDeckNameEdit: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = deckData.deckName,
            style = MaterialTheme.ldTypography.fontTitleM,
            color = Gray900
        )

        Icon(
            modifier = Modifier
                .padding(start = 4.dp)
                .clickable { onClickedDeckNameEdit() },
            imageVector = Icons.Filled.Edit,
            contentDescription = null,
            tint = Gray900
        )
    }
}

@Composable
fun YugiohCardGridList(
    cardList: List<YugiohCardData>,
    lazyGridState: LazyGridState
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(cardList.count()) { index ->
            GridYugiohCardItem(
                onClickedItem = {},
                yugiohCardData = cardList[index],
            )
        }
    }
}

@Composable
fun YugiohCardList(
    cardList: List<YugiohCardData>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(cardList.count()) { index ->
            ListYugiohCardItem(
                onClickedItem = {},
                yugiohCardData = cardList[index],
            )
        }
    }
}

@ThemePreviews
@Composable
internal fun DeckDetailScreenPreview(
    @PreviewParameter(DeckWithCardDataPreviewParameterProvider::class) deckWithCardData: DeckWithCardData,
) {
    LolDexTheme {
        LdBackGround {
            val deckDetailUiState = DeckDetailUiState.Success(deckWithCardData)
            val lazyGridState = rememberLazyGridState()

            DeckDetailScreen(
                deckDetailUiState = deckDetailUiState,
                lazyGridState = lazyGridState,
            )
        }
    }
}