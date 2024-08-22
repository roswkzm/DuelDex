package com.example.loldex.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.model.YugiohCardImage
import com.example.loldex.core.model.YugiohCardPrice
import com.example.loldex.core.ui.YugiohCardPreviewParameterData.yugiohCardData

class YugiohCardDataPreviewParameterProvider : PreviewParameterProvider<List<YugiohCardData>> {

    override val values: Sequence<List<YugiohCardData>> = sequenceOf(
        List(10) { yugiohCardData }
    )
}

object YugiohCardPreviewParameterData {

    private val yugiohCardImage: YugiohCardImage = YugiohCardImage(
        id = 46986421,
        imageUrl = "https://images.ygoprodeck.com/images/cards/46986421.jpg",
        imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986421.jpg",
        imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986421.jpg",
    )

    private val yugiohCardPrice: YugiohCardPrice = YugiohCardPrice(
        cardMarketPrice = "1000",
        tcgPlayerPrice = "1000",
        ebayPrice = "1000",
        amazonPrice = "1000",
        coolStuffIncPrice = "1000",
    )

    val yugiohCardData: YugiohCardData = YugiohCardData(
        id = 46986421,
        name = "\"Infernoble Arms - Durendal\"",
        type = "Normal Monster",
        frameType = "normal",
        desc = "''The ultimate wizard in terms of attack and defense.''",
        atk = 2500,
        def = 2100,
        level = 7,
        race = "Spellcaster",
        attribute = "DARK",
        archetype = "Dark Magician",
        ygoprodeckUrl = "https://ygoprodeck.com/card/dark-magician-4003",
        cardImages = listOf(yugiohCardImage),
        cardPrices = listOf(yugiohCardPrice)

    )
}