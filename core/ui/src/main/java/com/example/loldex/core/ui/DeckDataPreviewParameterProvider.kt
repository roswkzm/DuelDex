package com.example.loldex.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.ui.DeckPreviewParameterData.deckData

class DeckDataPreviewParameterProvider : PreviewParameterProvider<List<DeckData>> {
    override val values: Sequence<List<DeckData>> = sequenceOf(
        List(10) { deckData }
    )
}

object DeckPreviewParameterData {

    val deckData: DeckData = DeckData(
        id = 0,
        deckName = "Snake-Eye Deck"
    )
}