package com.example.loldex.feature.decks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.theme.Gray300
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.DeckData

@Composable
fun DeckListItem(
    deckData: DeckData,
    onClickedDeleteDeck: (DeckData) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        color = Gray300,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = deckData.deckName,
                style = MaterialTheme.ldTypography.fontLabelM,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onClickedDeleteDeck(deckData) },
                imageVector = Icons.Filled.DeleteForever,
                contentDescription = null,
                tint = Red
            )
        }
    }
}

@ThemePreviews()
@Composable
fun DeckListItemPreview() {
    val deckData = DeckData(deckName = "Deck Name")
    DeckListItem(
        deckData = deckData,
        onClickedDeleteDeck = {}
    )
}