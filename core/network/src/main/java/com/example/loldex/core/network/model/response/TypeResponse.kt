package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(
    val id: Int,
    val type: String
)