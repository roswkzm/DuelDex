package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val href: String,
    val transparent: Boolean
)