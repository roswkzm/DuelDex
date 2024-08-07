package com.example.loldex.core.data.repository

import com.example.loldex.core.network.model.response.CardListResponse
import kotlinx.coroutines.flow.Flow

interface YugiohRepository {

    fun getYugiohCardList(
        num: Int,
        offset: Int,
    ): Flow<CardListResponse>
}