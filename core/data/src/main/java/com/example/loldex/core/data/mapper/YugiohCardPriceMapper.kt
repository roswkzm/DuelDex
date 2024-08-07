package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.yugioh.YugiohCardPrice
import com.example.loldex.core.network.model.response.CardPriceResponse

fun CardPriceResponse.toData() = YugiohCardPrice(
    cardmarketPrice = cardmarketPrice,
    tcgplayerPrice = tcgplayerPrice,
    ebayPrice = ebayPrice,
    amazonPrice = amazonPrice,
    coolstuffincPrice = coolstuffincPrice,
)