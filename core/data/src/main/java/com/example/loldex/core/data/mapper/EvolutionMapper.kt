package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.EvolutionData
import com.example.loldex.core.network.model.response.EvolutionResponse

fun EvolutionResponse.toData() = EvolutionData(
    id = id,
    condition = condition,
    digimon = digimon,
    image = image,
    url = url
)