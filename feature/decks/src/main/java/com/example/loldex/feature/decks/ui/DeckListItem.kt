package com.example.loldex.feature.decks.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData

@Composable
fun DeckListItem(
    deckData: DeckData,
    onClickedDeleteDeck: (DeckData) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = deckData.deckName,
            style = MaterialTheme.ldTypography.fontLabelM,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            onClickedDeleteDeck(deckData)
        }) {
            Text(
                text = "삭제",
                style = MaterialTheme.ldTypography.fontLabelM,
                color = Color.Black
            )
        }
    }
}

@ThemePreviews
@Composable
fun DeckListItemPreview() {
    val deckData = DeckData(deckName = "Deck Name")
    DeckListItem(
        deckData = deckData,
        onClickedDeleteDeck = {}
    )
}