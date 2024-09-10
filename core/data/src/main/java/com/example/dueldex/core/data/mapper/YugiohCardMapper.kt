package com.example.dueldex.core.data.mapper

import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.network.model.response.CardDataResponse

fun CardDataResponse.toData() = YugiohCardData(
    id = id,
    name = name,
    type = type,
    frameType = frameType,
    desc = desc,
    atk = atk,
    def = def,
    level = level,
    race = race,
    attribute = attribute,
    archetype = archetype,
    ygoprodeckUrl = ygoprodeckUrl,
    cardImages = cardImages.map { it.toData() },
    cardPrices = cardPrices.map { it.toData() },
)

fun List<CardDataResponse>.toData() = this.map { it.toData() }