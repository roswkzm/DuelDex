package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AttributeResponse(
    val attribute: String,
    val id: Int
)