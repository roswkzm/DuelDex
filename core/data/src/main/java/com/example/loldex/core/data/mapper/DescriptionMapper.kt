package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.DescriptionData
import com.example.loldex.core.network.model.response.DescriptionResponse

fun DescriptionResponse.toData() = DescriptionData(
    description = description,
    language = language,
    origin = origin
)