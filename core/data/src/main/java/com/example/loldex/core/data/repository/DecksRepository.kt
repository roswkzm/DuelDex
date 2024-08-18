package com.example.loldex.core.data.repository

import com.example.loldex.core.model.YugiohCardData
import kotlinx.coroutines.flow.Flow

interface DecksRepository {

    val allCards: Flow<List<YugiohCardData>>

    suspend fun insertYugiohCard(yugiohCardData: YugiohCardData)

    suspend fun deleteYugiohCard(id: Long)
}