package com.example.loldex.feature.decks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.theme.Gray700
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
import com.example.loldex.core.designsystem.R as DesignR

@Composable
internal fun DeckDetailRoute(
    deckData: DeckData,
    onClickedCardItem: (String) -> Unit,
    viewModel: DeckDetailViewModel = hiltViewModel()
) {
    val deckDetailUiState by viewModel.deckDetailUiState.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()
    var isCardViewMode by remember { mutableStateOf(true) }

    LaunchedEffect(deckData) {
        viewModel.getDeckWithCards(deckData.id)
    }

    DeckDetailScreen(
        deckDetailUiState = deckDetailUiState,
        lazyGridState = lazyGridState,
        isCardViewMode = isCardViewMode,
        onClickedCardViewMode = { isCardViewMode = it },
        onClickedCardItem = onClickedCardItem,
        onClickedDeckNameEdit = viewModel::updateDeckName,
        onSwipedDeleteEvent = viewModel::deleteDeckCard
    )
}

@Composable
internal fun DeckDetailScreen(
    deckDetailUiState: DeckDetailUiState,
    lazyGridState: LazyGridState,
    isCardViewMode: Boolean,
    onClickedCardViewMode: (Boolean) -> Unit,
    onClickedCardItem: (String) -> Unit,
    onClickedDeckNameEdit: (DeckData) -> Unit,
    onSwipedDeleteEvent: (Long, Long) -> Unit,
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
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DeckNameTitleLayout(
                        deckData = deckData,
                        onClickedDeckNameEdit = onClickedDeckNameEdit,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ViewModeChangeLayout(
                            cardList = cardList,
                            isCardViewMode = isCardViewMode,
                            onClickCardViewMode = onClickedCardViewMode
                        )

                        if (isCardViewMode) {
                            YugiohCardGridList(
                                cardList = cardList,
                                lazyGridState = lazyGridState,
                                onClickedItem = onClickedCardItem,
                            )
                        } else {
                            YugiohCardList(
                                cardList = cardList,
                                onClickedItem = onClickedCardItem,
                                onSwipedDeleteEvent = { cardId ->
                                    onSwipedDeleteEvent(deckData.id, cardId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeckNameTitleLayout(
    deckData: DeckData,
    onClickedDeckNameEdit: (DeckData) -> Unit,
) {
    var isEditMode by remember { mutableStateOf(false) }
    var deckNameValue by remember { mutableStateOf(deckData.deckName) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isEditMode) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                value = deckNameValue,
                onValueChange = { deckNameValue = it },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { isEditMode = false },
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Edit Deck Name",
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                isEditMode = false
                                onClickedDeckNameEdit(
                                    deckData.copy(deckName = deckNameValue)
                                )
                            },
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Edit Deck Name",
                    )
                }
            )
        } else {
            Text(
                text = deckData.deckName,
                style = MaterialTheme.ldTypography.fontTitleM,
                color = Gray900
            )

            Icon(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { isEditMode = true },
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Gray900
            )
        }
    }
}

@Composable
fun ViewModeChangeLayout(
    cardList: List<YugiohCardData>,
    isCardViewMode: Boolean,
    onClickCardViewMode: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.deck_card_total_count),
                style = MaterialTheme.ldTypography.fontTitleS,
                color = Color.Black
            )

            Text(
                text = "${cardList.size}",
                style = MaterialTheme.ldTypography.fontTitleS,
                color = Gray700
            )


        }

        val viewModeIcon = if (isCardViewMode) {
            DesignR.drawable.grid_view
        } else {
            DesignR.drawable.list_view
        }

        Icon(
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    onClickCardViewMode(!isCardViewMode)
                },
            painter = painterResource(id = viewModeIcon),
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Composable
fun YugiohCardGridList(
    cardList: List<YugiohCardData>,
    lazyGridState: LazyGridState,
    onClickedItem: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(
            key = { index -> cardList[index].id },
            count = cardList.count()
        ) { index ->
            GridYugiohCardItem(
                onClickedItem = onClickedItem,
                yugiohCardData = cardList[index],
            )
        }
    }
}

@Composable
fun YugiohCardList(
    cardList: List<YugiohCardData>,
    onClickedItem: (String) -> Unit,
    onSwipedDeleteEvent: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(
            key = { index -> cardList[index].id },
            count = cardList.count()
        ) { index ->
            ListYugiohCardItem(
                onClickedItem = onClickedItem,
                yugiohCardData = cardList[index],
                onSwipedDeleteEvent = onSwipedDeleteEvent
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
            var isCardViewMode by remember { mutableStateOf(true) }

            DeckDetailScreen(
                deckDetailUiState = deckDetailUiState,
                lazyGridState = lazyGridState,
                isCardViewMode = isCardViewMode,
                onClickedCardViewMode = {},
                onClickedCardItem = {},
                onClickedDeckNameEdit = {},
                onSwipedDeleteEvent = { _, _ -> },
            )
        }
    }
}