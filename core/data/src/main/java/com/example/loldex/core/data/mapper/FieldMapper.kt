package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.FieldData
import com.example.loldex.core.network.model.response.FieldResponse

fun FieldResponse.toData() = FieldData(
    id = id,
    field = field,
    image = image,
)