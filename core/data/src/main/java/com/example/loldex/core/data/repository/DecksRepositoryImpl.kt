package com.example.loldex.core.data.repository

import com.example.loldex.core.database.dao.YugiohCardDao
import com.example.loldex.core.database.model.asEntity
import com.example.loldex.core.database.model.asExternalModel
import com.example.loldex.core.model.YugiohCardData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DecksRepositoryImpl @Inject constructor(
    private val yugiohCardDao: YugiohCardDao
) : DecksRepository {
    override val allCards: Flow<List<YugiohCardData>> =
        yugiohCardDao.getAllCards().map { entityList ->
            entityList.map {
                it.asExternalModel()
            }
        }

    override suspend fun insertYugiohCard(yugiohCardData: YugiohCardData) {
        yugiohCardDao.insertCard(yugiohCardData.asEntity())
    }

    override suspend fun deleteYugiohCard(id: Long) {
        yugiohCardDao.deleteCardById(id)
    }

}