package com.example.loldex.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: Int,
    val error: String
)
