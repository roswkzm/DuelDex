package com.example.loldex.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardPriceResponse(
    @SerialName("cardmarket_price")
    val cardmarketPrice: String,
    @SerialName("tcgplayer_price")
    val tcgplayerPrice: String,
    @SerialName("ebay_price")
    val ebayPrice: String,
    @SerialName("amazon_price")
    val amazonPrice: String,
    @SerialName("coolstuffinc_price")
    val coolstuffincPrice: String
)