package com.example.dueldex.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dueldex.core.database.model.YugiohCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface YugiohCardDao {
    // 모든 카드 조회
    @Query("SELECT * FROM yugioh_card_data")
    fun getAllCards(): Flow<List<YugiohCardEntity>>

    // 카드 삽입 (중복 시 덮어쓰기)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: YugiohCardEntity): Long

    // ID로 특정 카드 삭제
    @Query("DELETE FROM yugioh_card_data WHERE id = :id")
    suspend fun deleteCardById(id: Long)
}