package com.example.loldex.core.network

import com.example.loldex.core.network.model.DataResponse
import com.example.loldex.core.network.model.ListResponse
import com.example.loldex.core.network.model.response.DisneyCharacterResponse
import com.skydoves.sandwich.ApiResponse

interface DisneyNetworkDataSource {

    suspend fun getCharacter(
        page: Int,
        pageSize: Int,
    ): ApiResponse<ListResponse<List<DisneyCharacterResponse>>>

    suspend fun getCharacterById(
        id: Int,
    ): ApiResponse<DataResponse<DisneyCharacterResponse>>

    suspend fun getCharacterByName(
        name: String
    ): ApiResponse<ListResponse<List<DisneyCharacterResponse>>>
}