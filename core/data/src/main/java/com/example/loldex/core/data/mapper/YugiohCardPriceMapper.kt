package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.YugiohCardPrice
import com.example.loldex.core.network.model.response.CardPriceResponse

fun CardPriceResponse.toData() = YugiohCardPrice(
    cardMarketPrice = cardmarketPrice,
    tcgPlayerPrice = tcgplayerPrice,
    ebayPrice = ebayPrice,
    amazonPrice = amazonPrice,
    coolStuffIncPrice = coolstuffincPrice,
)