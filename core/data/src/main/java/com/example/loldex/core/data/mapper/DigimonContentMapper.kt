package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.DigimonContentData
import com.example.loldex.core.network.model.response.DigimonContentResponse

fun DigimonContentResponse.toData() = DigimonContentData(
    href = href,
    id = id,
    image = image,
    name = name
)