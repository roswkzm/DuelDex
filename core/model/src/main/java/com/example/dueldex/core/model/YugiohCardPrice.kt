package com.example.dueldex.core.model

data class YugiohCardPrice(
    val cardMarketPrice: String,
    val tcgPlayerPrice: String,
    val ebayPrice: String,
    val amazonPrice: String,
    val coolStuffIncPrice: String
)

fun YugiohCardPrice.toPriceList(): List<Float> {
    return listOf(
        cardMarketPrice.toFloat(),
        tcgPlayerPrice.toFloat(),
        ebayPrice.toFloat(),
        amazonPrice.toFloat(),
        coolStuffIncPrice.toFloat(),
    )
}

fun YugiohCardPrice.toMarketNameList(): List<String> {
    return listOf(
        "CardMarket",
        "TCGPlayer",
        "eBay",
        "Amazon",
        "CoolStuffInc"
    )
}