package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.DisneyCharacterData
import com.example.loldex.core.network.model.response.DisneyCharacterResponse

fun DisneyCharacterResponse.toData() = DisneyCharacterData(
    id = id,
    url = url,
    name = name,
    sourceUrl = sourceUrl,
    films = films,
    shortFilms = shortFilms,
    tvShows = tvShows,
    videoGames = videoGames,
    parkAttractions = parkAttractions,
    allies = allies,
    enemies = enemies,
    imageUrl = imageUrl,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<DisneyCharacterResponse>.toData(): List<DisneyCharacterData> {
    return this.map {
        DisneyCharacterData(
            id = it.id,
            url = it.url,
            name = it.name,
            sourceUrl = it.sourceUrl,
            films = it.films,
            shortFilms = it.shortFilms,
            tvShows = it.tvShows,
            videoGames = it.videoGames,
            parkAttractions = it.parkAttractions,
            allies = it.allies,
            enemies = it.enemies,
            imageUrl = it.imageUrl,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}