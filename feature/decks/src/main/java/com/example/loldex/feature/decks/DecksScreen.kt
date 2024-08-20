package com.example.loldex.feature.decks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.ui.util.statusBarPadding
import com.example.loldex.feature.decks.ui.DeckListItem

@Composable
internal fun DecksRoute(
    viewModel: DecksViewModel = hiltViewModel()
) {
    val decksUiState by viewModel.allDecks.collectAsStateWithLifecycle()
    var insertDeckName by remember { mutableStateOf("") }
    DecksScreen(
        decksUiState = decksUiState,
        onClickedDeleteDeck = viewModel::deleteDeck,
        onClickedInsertDeck = {
            viewModel.insertDeck(insertDeckName)
            insertDeckName = ""
        },
        insertDeckName = insertDeckName,
        onChangeValue = { insertDeckName = it }
    )
}

@Composable
internal fun DecksScreen(
    decksUiState: DecksUiState,
    onClickedDeleteDeck: (DeckData) -> Unit,
    onClickedInsertDeck: () -> Unit,
    insertDeckName: String,
    onChangeValue: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {
        when (decksUiState) {
            DecksUiState.Loading -> {
                Text(text = "Loading")
            }

            is DecksUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        TextField(
                            value = insertDeckName,
                            onValueChange = onChangeValue
                        )
                        Button(
                            onClick = onClickedInsertDeck
                        ) {
                            Text(
                                text = "추가",
                                style = MaterialTheme.ldTypography.fontLabelM,
                                color = Color.Black
                            )
                        }
                    }

                    val deckList = decksUiState.deckList
                    if (deckList.size == 0) {
                        Text(text = "Deck Size 0")
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
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