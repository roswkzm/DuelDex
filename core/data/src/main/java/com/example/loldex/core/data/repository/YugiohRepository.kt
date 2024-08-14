package com.example.loldex.core.data.repository

import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.network.model.response.CardPagingListResponse
import kotlinx.coroutines.flow.Flow

interface YugiohRepository {

    fun getYugiohPagingList(
        num: Int,
        offset: Int,
    ): Flow<CardPagingListResponse>

    fun getYugiohCardDataById(
        id: Long
    ): Flow<YugiohCardData>

    fun getYugiohCardDataByName(
        name: String
    ): Flow<YugiohCardData>

    fun getYugiohCardDataBySearchString(
        searchString: String
    ): Flow<YugiohCardData>
}