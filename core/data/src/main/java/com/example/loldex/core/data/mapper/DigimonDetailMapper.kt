package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.DigimonDetailData
import com.example.loldex.core.network.model.response.DigimonDetailResponse

fun DigimonDetailResponse.toData() = DigimonDetailData(
    id = id,
    name = name,
    attributes = attributes.map { it.toData() },
    descriptions = descriptions.map { it.toData() },
    fields = fields.map { it.toData() },
    imageResponses = images.map { it.toData() },
    nextEvolutions = nextEvolutions.map { it.toData() },
    priorEvolutions = priorEvolutions.map { it.toData() },
    releaseDate = releaseDate,
    skills = skills.map { it.toData() },
    types = types.map { it.toData() },
)