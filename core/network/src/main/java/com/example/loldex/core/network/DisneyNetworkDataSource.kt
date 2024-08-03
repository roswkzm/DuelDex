package com.example.loldex.core.network

interface DisneyNetworkDataSource {

    suspend fun getCharacter(
        page: Int,
        pageSize: Int,
    )

    suspend fun getCharacterById(
        id: Int,
    )

    suspend fun getCharacterByName(
        name: String
    )
}