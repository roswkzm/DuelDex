package com.example.loldex.core.ui.preview_parameter_provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.ui.preview_parameter_provider.DeckPreviewParameterData.deckData

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