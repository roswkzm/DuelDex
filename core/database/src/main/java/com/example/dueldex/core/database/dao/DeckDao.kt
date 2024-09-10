package com.example.dueldex.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.dueldex.core.database.model.DeckCardCrossRef
import com.example.dueldex.core.database.model.DeckEntity
import com.example.dueldex.core.database.model.DeckWithCards
import com.example.dueldex.core.database.model.YugiohCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {

    // 모든 Deck 조회
    @Query("SELECT * FROM decks")
    fun getAllDecks(): Flow<List<DeckEntity>>

    // Deck 생성
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeck(deckEntity: DeckEntity): Long

    // Deck 삭제
    @Delete
    suspend fun deleteDeck(deckEntity: DeckEntity)

    // Deck의 이름 업데이트
    @Update
    suspend fun updateDeck(deckEntity: DeckEntity)

    // 특정 Deck에 존재하는 Card 조회
    @Transaction
    @Query("SELECT * FROM decks WHERE id = :deckId")
    fun getDeckWithCards(deckId: Long): Flow<DeckWithCards>

    // 특정 Deck에 Card 추가
    @Transaction
    suspend fun insertDeckCard(deckId: Long, yugiohCardEntity: YugiohCardEntity) {
        insertOrUpdateCard(yugiohCardEntity)

        val crossRef = DeckCardCrossRef(deckId = deckId, cardId = yugiohCardEntity.id)
        insertDeckCardCrossRef(crossRef)
    }

    // Deck의 Card 삭제
    @Query("DELETE FROM DeckCardCrossRef WHERE deckId = :deckId AND cardId = :cardId")
    suspend fun deleteCardFromDeck(deckId: Long, cardId: Long)

    // Card Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCard(yugiohCardEntity: YugiohCardEntity)

    // Deck과 Card에 대한 관계 저장
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeckCardCrossRef(crossRef: DeckCardCrossRef)
}
