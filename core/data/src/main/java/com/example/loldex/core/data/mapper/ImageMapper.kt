package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.ImageData
import com.example.loldex.core.network.model.response.ImageResponse

fun ImageResponse.toData() = ImageData(
    href = href,
    transparent = transparent,
)