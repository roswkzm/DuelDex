package com.example.dueldex.core.data.repository

import com.example.dueldex.core.model.DeckData
import com.example.dueldex.core.model.DeckWithCardData
import com.example.dueldex.core.model.YugiohCardData
import kotlinx.coroutines.flow.Flow

interface DecksRepository {

    val allDecks: Flow<List<DeckData>>

    suspend fun insertDeck(deckName: String)

    suspend fun deleteDeck(deckData: DeckData)

    suspend fun updateDeckName(deckData: DeckData)

    suspend fun getDeckWithCards(deckId: Long): Flow<DeckWithCardData>

    suspend fun insertDeckCard(deckId: Long, yugiohCardData: YugiohCardData)

    suspend fun deleteDeckCard(deckId: Long, cardId: Long)
}