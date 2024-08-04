package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.AttributeData
import com.example.loldex.core.network.model.response.AttributeResponse

fun AttributeResponse.toData() = AttributeData(
    id = id,
    attribute = attribute,
)