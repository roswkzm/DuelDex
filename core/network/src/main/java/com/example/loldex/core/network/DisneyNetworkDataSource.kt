package com.example.loldex.core.network

import com.example.loldex.core.network.model.DataResponse
import com.example.loldex.core.network.model.ListResponse
import com.example.loldex.core.network.model.response.DisneyCharacter
import com.skydoves.sandwich.ApiResponse

interface DisneyNetworkDataSource {

    suspend fun getCharacter(
        page: Int,
        pageSize: Int,
    ): ApiResponse<ListResponse<List<DisneyCharacter>>>

    suspend fun getCharacterById(
        id: Int,
    ): ApiResponse<DataResponse<DisneyCharacter>>

    suspend fun getCharacterByName(
        name: String
    ): ApiResponse<ListResponse<List<DisneyCharacter>>>
}