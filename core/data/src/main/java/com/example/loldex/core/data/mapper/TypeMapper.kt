package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.TypeData
import com.example.loldex.core.network.model.response.TypeResponse

fun TypeResponse.toData() = TypeData(
    id = id,
    type = type
)