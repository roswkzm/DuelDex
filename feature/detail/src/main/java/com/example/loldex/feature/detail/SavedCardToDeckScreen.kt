package com.example.loldex.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.Black
import com.example.loldex.core.designsystem.theme.Secondary
import com.example.loldex.core.designsystem.theme.Text0
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.DeckListItem

@Composable
internal fun SavedCardToDeckScreen(
    yugiohCardData: YugiohCardData,
    viewModel: SavedCardToDeckViewModel = hiltViewModel(),
) {
    val savedDecksUiState by viewModel.allDecks.collectAsStateWithLifecycle()

    SavedCardToDeckContent(
        yugiohCardData = yugiohCardData,
        savedDecksUiState = savedDecksUiState,
        onClickedDeleteDeck = viewModel::deleteDeck,
    )
}

@Composable
internal fun SavedCardToDeckContent(
    yugiohCardData: YugiohCardData,
    savedDecksUiState: SavedDecksUiState,
    onClickedDeleteDeck: (DeckData) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (savedDecksUiState) {
            SavedDecksUiState.Loading -> {
                Text(text = "Loading")
            }

            is SavedDecksUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    val deckList = savedDecksUiState.deckList

                    SavedDeckTitleLayout(
                        onChangeIsShowCreateDeckDialog = {}
                    )

                    if (deckList.isEmpty()) {
                        Text(text = "Deck Size 0")
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(deckList.size) { index ->
                                DeckListItem(
                                    deckData = deckList[index],
                                    onClickedDeleteDeck = onClickedDeleteDeck
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SavedDeckTitleLayout(
    onChangeIsShowCreateDeckDialog: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.saved_card_deck_title),
                style = MaterialTheme.ldTypography.fontLabelXL,
                color = Black
            )
        }

        LdButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { onChangeIsShowCreateDeckDialog(true) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                disabledContainerColor = Secondary
            ),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Deck",
                    tint = Text0
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.add_deck_btn_text),
                    style = MaterialTheme.ldTypography.fontBodyS,
                    color = Text0
                )
            }
        )
    }
}