package com.example.loldex.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.model.YugiohCardImage
import com.example.loldex.core.model.YugiohCardPrice
import com.example.loldex.core.ui.PreviewParameterData.yugiohCardData

class YugiohCardDataPreviewParameterProvider : PreviewParameterProvider<List<YugiohCardData>> {

    override val values: Sequence<List<YugiohCardData>> = sequenceOf(
        List(10) { yugiohCardData }
    )
}

object PreviewParameterData {

    private val yugiohCardImage: YugiohCardImage = YugiohCardImage(
        id = 1,
        imageUrl = "Test Image Url",
        imageUrlSmall = "Test Image Url Small",
        imageUrlCropped = "Test Image Url Cropped",
    )

    private val yugiohCardPrice: YugiohCardPrice = YugiohCardPrice(
        cardmarketPrice = "1000",
        tcgplayerPrice = "1000",
        ebayPrice = "1000",
        amazonPrice = "1000",
        coolstuffincPrice = "1000",
    )

    val yugiohCardData: YugiohCardData = YugiohCardData(
        id = 1,
        name = "Test Name",
        type = "Effect Monster",
        frameType = "effect",
        desc = "Card Description",
        atk = 100,
        def = 100,
        level = 5,
        race = "Dragon",
        attribute = "Test Attribute",
        archetype = "Text Archetype",
        ygoprodeckUrl = "Ygoprodeck Url",
        cardImages = listOf(yugiohCardImage),
        cardPrices = listOf(yugiohCardPrice)

    )
}