package com.example.loldex.core.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["deckId", "cardId"])
data class DeckCardCrossRef(
    val deckId: Long,
    val cardId: Long
)