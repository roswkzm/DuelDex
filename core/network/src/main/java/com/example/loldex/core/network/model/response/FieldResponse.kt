package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class FieldResponse(
    val field: String,
    val id: Int,
    val image: String
)