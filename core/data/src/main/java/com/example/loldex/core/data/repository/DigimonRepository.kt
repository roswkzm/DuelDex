package com.example.loldex.core.data.repository

import com.example.loldex.core.network.model.response.DigimonDetailResponse
import com.example.loldex.core.network.model.response.DigimonPagingResponse
import kotlinx.coroutines.flow.Flow

interface DigimonRepository {

    fun getDigimonList(
        page: Int,
        pageSize: Int,
    ): Flow<DigimonPagingResponse>

    fun getDigimonById(
        id: Int,
    ): Flow<DigimonDetailResponse>

    fun getDigimonByName(
        name: String,
    ): Flow<DigimonDetailResponse>
}