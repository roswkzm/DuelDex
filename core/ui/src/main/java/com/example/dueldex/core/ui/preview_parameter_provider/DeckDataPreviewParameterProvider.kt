package com.example.dueldex.core.ui.preview_parameter_provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.dueldex.core.model.DeckData

class DeckDataPreviewParameterProvider : PreviewParameterProvider<List<DeckData>> {
    override val values: Sequence<List<DeckData>> = sequenceOf(
        List(10) { index ->
            DeckData(
                id = index.toLong(),
                deckName = "Snake-Eye Deck $index"
            )
        }
    )
}