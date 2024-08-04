package com.example.loldex.core.network

import com.example.loldex.core.network.model.response.DigimonDetailResponse
import com.example.loldex.core.network.model.response.DigimonPagingResponse
import com.skydoves.sandwich.ApiResponse

interface DigimonNetworkDataSource {

    suspend fun getDigimonList(
        page: Int,
        pageSize: Int,
    ): ApiResponse<DigimonPagingResponse>

    suspend fun getDigimonById(
        id: Int
    ): ApiResponse<DigimonDetailResponse>

    suspend fun getDigimonByName(
        name: String
    ): ApiResponse<DigimonDetailResponse>
}