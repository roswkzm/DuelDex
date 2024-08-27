package com.example.loldex.feature.decks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.Black
import com.example.loldex.core.designsystem.theme.Gray500
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.Neutral30
import com.example.loldex.core.designsystem.theme.Text0
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.ui.CreateDeckDialog
import com.example.loldex.core.ui.DeckListItem
import com.example.loldex.core.ui.preview_parameter_provider.DeckDataPreviewParameterProvider
import com.example.loldex.core.ui.util.statusBarPadding

@Composable
internal fun DecksRoute(
    viewModel: DecksViewModel = hiltViewModel(),
    onClickedDeck: (DeckData) -> Unit,
) {
    val decksUiState by viewModel.allDecks.collectAsStateWithLifecycle()
    var insertDeckName by remember { mutableStateOf("") }
    var isShowCreateDeckDialog by remember { mutableStateOf(false) }

    DecksScreen(
        decksUiState = decksUiState,
        onClickedDeleteDeck = viewModel::deleteDeck,
        onClickedInsertDeck = {
            viewModel.insertDeck(it)
            isShowCreateDeckDialog = false
        },
        insertDeckName = insertDeckName,
        onChangeValue = { insertDeckName = it },
        isShowCreateDeckDialog = isShowCreateDeckDialog,
        onChangeIsShowCreateDeckDialog = { isShowCreateDeckDialog = it },
        onClickedDeck = onClickedDeck
    )
}

@Composable
internal fun DecksScreen(
    decksUiState: DecksUiState,
    onClickedDeleteDeck: (DeckData) -> Unit,
    onClickedInsertDeck: (String) -> Unit,
    insertDeckName: String,
    onChangeValue: (String) -> Unit,
    isShowCreateDeckDialog: Boolean,
    onChangeIsShowCreateDeckDialog: (Boolean) -> Unit,
    onClickedDeck: (DeckData) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {
        var isError by remember { mutableStateOf(false) }
        when (decksUiState) {
            DecksUiState.Loading -> {
                Text(text = "Loading")
            }

            is DecksUiState.Success -> {
                val deckList = decksUiState.deckList
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    DeckTotalTitleLayout(
                        deckList = deckList,
                        onChangeIsShowCreateDeckDialog = onChangeIsShowCreateDeckDialog
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
                                    onClickedDeck = onClickedDeck,
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
                            onChangeValue("")
                            onChangeIsShowCreateDeckDialog(false)
                        },
                        onClickedConfirm = {
                            onChangeValue("")
                            onClickedInsertDeck(it)
                        },
                        inputValue = insertDeckName,
                        onValueChange = onChangeValue,
                        isError = isError
                    )
                }
            }
        }
    }
}

@Composable
fun DeckTotalTitleLayout(
    deckList: List<DeckData>,
    onChangeIsShowCreateDeckDialog: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.total_deck_list),
                style = MaterialTheme.ldTypography.fontLabelXL,
                color = Black
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = "${deckList.size}",
                style = MaterialTheme.ldTypography.fontLabelL,
                color = Gray500
            )
        }

        LdButton(
            onClick = { onChangeIsShowCreateDeckDialog(true) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Neutral30,
                disabledContainerColor = Neutral30
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
fun DecksScreenPrewview(
    @PreviewParameter(DeckDataPreviewParameterProvider::class) deckList: List<DeckData>
) {
    val decksUiState = DecksUiState.Success(deckList)
    LolDexTheme {
        LdBackGround {
            DecksScreen(
                decksUiState = decksUiState,
                onClickedDeleteDeck = {},
                onClickedInsertDeck = {},
                insertDeckName = "",
                onChangeValue = {},
                isShowCreateDeckDialog = false,
                onChangeIsShowCreateDeckDialog = {},
                onClickedDeck = {}
            )
        }
    }
}