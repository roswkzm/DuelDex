package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionResponse(
    val description: String,
    val language: String,
    val origin: String
)