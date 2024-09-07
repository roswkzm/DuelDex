package com.example.loldex.feature.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.Black
import com.example.loldex.core.designsystem.theme.Secondary
import com.example.loldex.core.designsystem.theme.Text0
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.CreateDeckDialog
import com.example.loldex.core.ui.DeckListItem
import com.example.loldex.core.ui.preview_parameter_provider.DeckDataPreviewParameterProvider

@Composable
internal fun SavedCardToDeckScreen(
    yugiohCardData: YugiohCardData,
    viewModel: SavedCardToDeckViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val savedDecksUiState by viewModel.allDecks.collectAsStateWithLifecycle()

    // Create Deck State
    var isShowCreateDeckDialog by remember { mutableStateOf(false) }
    var insertDeckName by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.cardSaveResultFlow.collect { isSuccess ->
            if (isSuccess) {
                Toast.makeText(context, "Card saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to save card.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    SavedCardToDeckContent(
        isShowDialogChange = { isShowCreateDeckDialog = it },
        savedDecksUiState = savedDecksUiState,
        onClickedDeleteDeck = viewModel::deleteDeck,
        onClickedInsertDeck = {
            viewModel.insertDeck(it)
            isShowCreateDeckDialog = false
        },
        onClickedInsertCardToDeck = { deckData ->
            viewModel.insertDeckCard(deckData.id, yugiohCardData)
        },
        isShowCreateDeckDialog = isShowCreateDeckDialog,
        onChangeIsShowCreateDeckDialog = { isShowCreateDeckDialog = it },
        insertDeckName = insertDeckName,
        onChangeDeckNameValue = { insertDeckName = it },
    )
}

@Composable
internal fun SavedCardToDeckContent(
    isShowDialogChange: (Boolean) -> Unit,
    savedDecksUiState: SavedDecksUiState,
    onClickedDeleteDeck: (DeckData) -> Unit,
    onClickedInsertDeck: (String) -> Unit,
    onClickedInsertCardToDeck: (DeckData) -> Unit,
    isShowCreateDeckDialog: Boolean,
    onChangeIsShowCreateDeckDialog: (Boolean) -> Unit,
    insertDeckName: String,
    onChangeDeckNameValue: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
    ) {
        // isError 상태를 위한 추가
        var isError by remember { mutableStateOf(false) }

        when (savedDecksUiState) {
            SavedDecksUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier)
                }
            }

            is SavedDecksUiState.Success -> {
                val deckList = savedDecksUiState.deckList
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    SavedDeckTitleLayout(
                        onChangeIsShowCreateDeckDialog = {
                            isShowDialogChange(true)
                        }
                    )

                    if (deckList.isEmpty()) {
                        Text(text = "Deck Size 0")
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(
                                key = { index -> deckList[index].id },
                                count = deckList.size
                            ) { index ->
                                DeckListItem(
                                    deckData = deckList[index],
                                    onClickedDeck = onClickedInsertCardToDeck,
                                    onClickedDeleteDeck = onClickedDeleteDeck
                                )
                            }
                        }
                    }
                }

                if (isShowCreateDeckDialog) {
                    // insertDeckName 상태에 따라 isError 상태 업데이트
                    isError =
                        insertDeckName.isEmpty() || deckList.any { it.deckName == insertDeckName }
                    CreateDeckDialog(
                        onDismiss = {
                            onChangeDeckNameValue("")
                            onChangeIsShowCreateDeckDialog(false)
                        },
                        onClickedConfirm = {
                            onChangeDeckNameValue("")
                            onClickedInsertDeck(it)
                        },
                        inputValue = insertDeckName,
                        onValueChange = onChangeDeckNameValue,
                        isError = isError,
                    )
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
            .fillMaxWidth()
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

@ThemePreviews
@Composable
fun SavedCardToDeckContentPreview(
    @PreviewParameter(DeckDataPreviewParameterProvider::class) deckList: List<DeckData>
) {
    val savedDecksUiState = SavedDecksUiState.Success(deckList)
    SavedCardToDeckContent(
        isShowDialogChange = { },
        savedDecksUiState = savedDecksUiState,
        onClickedDeleteDeck = {},
        onClickedInsertDeck = {},
        onClickedInsertCardToDeck = {},
        isShowCreateDeckDialog = false,
        onChangeIsShowCreateDeckDialog = { },
        insertDeckName = "",
        onChangeDeckNameValue = { },
    )
}