package com.example.loldex.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.loldex.core.model.DeckData

@Entity(tableName = "decks")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "deck_name")
    val deckName: String
)

fun DeckEntity.asExternalModel() = DeckData(
    id = id,
    deckName = deckName
)

fun DeckData.asEntity() = DeckEntity(
    id = id,
    deckName = deckName
)