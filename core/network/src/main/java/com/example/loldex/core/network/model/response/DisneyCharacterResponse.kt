package com.example.loldex.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisneyCharacterResponse(
    @SerialName("_id")
    val id: Int,
    val url: String,
    val name: String,
    val sourceUrl: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val parkAttractions: List<String>,
    val allies: List<String>,
    val enemies: List<String>,
    val imageUrl: String,
    val createdAt: String,
    val updatedAt: String,
)