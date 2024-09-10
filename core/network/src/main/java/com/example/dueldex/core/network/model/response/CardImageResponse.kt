package com.example.dueldex.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardImageResponse(
    val id: Long,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("image_url_small")
    val imageUrlSmall: String,
    @SerialName("image_url_cropped")
    val imageUrlCropped: String
)