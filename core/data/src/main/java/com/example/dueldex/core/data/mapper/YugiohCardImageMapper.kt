package com.example.dueldex.core.data.mapper

import com.example.dueldex.core.model.YugiohCardImage
import com.example.dueldex.core.network.model.response.CardImageResponse

fun CardImageResponse.toData() = YugiohCardImage(
    id = id,
    imageUrl = imageUrl,
    imageUrlSmall = imageUrlSmall,
    imageUrlCropped = imageUrlCropped,
)