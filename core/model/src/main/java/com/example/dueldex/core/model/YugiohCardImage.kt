package com.example.dueldex.core.model

import kotlinx.serialization.Serializable

@Serializable
data class YugiohCardImage(
    val id: Long,
    val imageUrl: String,
    val imageUrlSmall: String,
    val imageUrlCropped: String
)