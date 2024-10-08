package com.example.dueldex.core.ui.preview_parameter_provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.dueldex.core.model.DeckData
import com.example.dueldex.core.model.DeckWithCardData
import com.example.dueldex.core.ui.preview_parameter_provider.DeckWithCardDataPreviewParameterProvider.DeckWithCardPreviewParameterData.deckWithCardData

class DeckWithCardDataPreviewParameterProvider : PreviewParameterProvider<DeckWithCardData> {
    override val values: Sequence<DeckWithCardData> = sequenceOf(deckWithCardData)


    object DeckWithCardPreviewParameterData {

        private val deckData: DeckData = DeckData(
            id = 0,
            deckName = "Test Deck"
        )

        val deckWithCardData: DeckWithCardData = DeckWithCardData(
            deckData = deckData,
            yugiohCardList = List(10) {
                YugiohCardPreviewParameterData.yugiohCardData.copy(
                    id = YugiohCardPreviewParameterData.yugiohCardData.id + it
                )
            }
        )
    }
}