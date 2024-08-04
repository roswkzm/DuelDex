package com.example.loldex.core.model

data class DisneyCharacterData(
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