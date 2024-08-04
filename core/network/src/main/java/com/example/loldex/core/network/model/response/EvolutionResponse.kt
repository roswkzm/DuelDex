package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class EvolutionResponse(
    val condition: String,
    val digimon: String,
    val id: Int,
    val image: String,
    val url: String
)