package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.network.model.response.CardResponse

fun CardResponse.toData() = YugiohCardData(
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