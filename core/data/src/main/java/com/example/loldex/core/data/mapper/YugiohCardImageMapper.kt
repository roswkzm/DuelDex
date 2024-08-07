package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.yugioh.YugiohCardImage
import com.example.loldex.core.network.model.response.CardImageResponse

fun CardImageResponse.toData() = YugiohCardImage(
    id = id,
    imageUrl = imageUrl,
    imageUrlSmall = imageUrlSmall,
    imageUrlCropped = imageUrlCropped,
)