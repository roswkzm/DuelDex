package com.example.dueldex.feature.decks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.dueldex.core.designsystem.component.DdBackGround
import com.example.dueldex.core.designsystem.theme.Gray700
import com.example.dueldex.core.designsystem.theme.Gray900
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.core.model.DeckData
import com.example.dueldex.core.model.DeckWithCardData
import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.ui.GridYugiohCardItem
import com.example.dueldex.core.ui.ListYugiohCardItem
import com.example.dueldex.core.ui.UiStateFailedScreen
import com.example.dueldex.core.ui.preview_parameter_provider.DeckWithCardDataPreviewParameterProvider
import com.example.dueldex.core.ui.util.statusBarPadding
import com.example.dueldex.core.designsystem.R as DesignR

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
        onDeleteCard = viewModel::deleteDeckCard,
        onClickedRetry = {
            viewModel.getDeckWithCards(deckData.id)
        }
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
    onDeleteCard: (Long, Long) -> Unit,
    onClickedRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {

        when (deckDetailUiState) {
            DeckDetailUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier)
                }
            }

            is DeckDetailUiState.Error -> {
                UiStateFailedScreen(
                    onClickedRetry = onClickedRetry
                )
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
                                onDeleteCard = { cardId ->
                                    onDeleteCard(deckData.id, cardId)
                                }
                            )
                        } else {
                            YugiohCardList(
                                cardList = cardList,
                                onClickedItem = onClickedCardItem,
                                onDeleteCard = { cardId ->
                                    onDeleteCard(deckData.id, cardId)
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
                style = MaterialTheme.ddTypography.fontTitleM,
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
                style = MaterialTheme.ddTypography.fontTitleS,
                color = Color.Black
            )

            Text(
                text = "${cardList.size}",
                style = MaterialTheme.ddTypography.fontTitleS,
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
    onDeleteCard: (Long) -> Unit,
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
            Box {
                GridYugiohCardItem(
                    onClickedItem = onClickedItem,
                    yugiohCardData = cardList[index],
                )
                Icon(
                    modifier = Modifier
                        .padding(top = 10.dp, end = 10.dp)
                        .size(28.dp)
                        .background(
                            Color.White.copy(alpha = 0.5f),
                            CircleShape
                        )
                        .padding(2.dp)
                        .align(Alignment.TopEnd)
                        .clickable { onDeleteCard(cardList[index].id) },
                    imageVector = Icons.Filled.Delete,
                    tint = Color.Red.copy(alpha = 0.8f),
                    contentDescription = "Delete Card",
                )
            }
        }
    }
}

@Composable
fun YugiohCardList(
    cardList: List<YugiohCardData>,
    onClickedItem: (String) -> Unit,
    onDeleteCard: (Long) -> Unit,
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
                onSwipedDeleteEvent = onDeleteCard
            )
        }
    }
}

@ThemePreviews
@Composable
internal fun DeckDetailScreenPreview(
    @PreviewParameter(DeckWithCardDataPreviewParameterProvider::class) deckWithCardData: DeckWithCardData,
) {
    DuelDexTheme {
        DdBackGround {
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
                onDeleteCard = { _, _ -> },
                onClickedRetry = {}
            )
        }
    }
}