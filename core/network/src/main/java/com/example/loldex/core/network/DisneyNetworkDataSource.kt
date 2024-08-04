package com.example.loldex.core.network

import com.example.loldex.core.network.model.DisneyDataResponse
import com.example.loldex.core.network.model.response.DisneyCharacterResponse
import com.skydoves.sandwich.ApiResponse

interface DisneyNetworkDataSource {

    suspend fun getCharacter(
        page: Int,
        pageSize: Int,
    ): ApiResponse<DisneyDataResponse<List<DisneyCharacterResponse>>>

    suspend fun getCharacterById(
        id: Int,
    ): ApiResponse<DisneyDataResponse<DisneyCharacterResponse>>

    suspend fun getCharacterByName(
        name: String
    ): ApiResponse<DisneyDataResponse<List<DisneyCharacterResponse>>>
}