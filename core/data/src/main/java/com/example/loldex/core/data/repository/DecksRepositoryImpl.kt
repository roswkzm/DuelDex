package com.example.loldex.core.data.repository

import com.example.loldex.core.database.dao.DeckDao
import com.example.loldex.core.database.model.asEntity
import com.example.loldex.core.database.model.asExternalModel
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.model.DeckWithCardData
import com.example.loldex.core.model.YugiohCardData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DecksRepositoryImpl @Inject constructor(
    private val deckDao: DeckDao,
) : DecksRepository {

    override val allDecks: Flow<List<DeckData>> =
        deckDao.getAllDecks().map { deckList ->
            deckList.map {
                it.asExternalModel()
            }
        }

    override suspend fun insertDeck(deckName: String) {
        val deckData = DeckData(deckName = deckName)
        deckDao.insertDeck(deckData.asEntity())
    }

    override suspend fun deleteDeck(deckData: DeckData) {
        deckDao.deleteDeck(deckData.asEntity())
    }

    override suspend fun updateDeckName(deckData: DeckData) {
        deckDao.updateDeck(deckData.asEntity())
    }

    override suspend fun getDeckWithCards(deckId: Long): Flow<DeckWithCardData> {
        return deckDao.getDeckWithCards(deckId).map { it.asExternalModel() }
    }

    override suspend fun insertDeckCard(deckId: Long, yugiohCardData: YugiohCardData) {
        deckDao.insertDeckCard(deckId, yugiohCardData.asEntity())
    }

    override suspend fun deleteDeckCard(deckId: Long, cardId: Long) {
        deckDao.deleteCardFromDeck(deckId, cardId)
    }
}