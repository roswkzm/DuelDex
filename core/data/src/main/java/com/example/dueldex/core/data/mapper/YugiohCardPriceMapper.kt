package com.example.dueldex.core.data.mapper

import com.example.dueldex.core.model.YugiohCardPrice
import com.example.dueldex.core.network.model.response.CardPriceResponse

fun CardPriceResponse.toData() = YugiohCardPrice(
    cardMarketPrice = cardmarketPrice,
    tcgPlayerPrice = tcgplayerPrice,
    ebayPrice = ebayPrice,
    amazonPrice = amazonPrice,
    coolStuffIncPrice = coolstuffincPrice,
)