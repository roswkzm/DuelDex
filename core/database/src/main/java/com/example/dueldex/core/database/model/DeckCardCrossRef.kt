package com.example.dueldex.core.database.model

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["deckId", "cardId"],
    indices = [Index(value = ["cardId"]), Index(value = ["deckId"])]
)
data class DeckCardCrossRef(
    val deckId: Long,
    val cardId: Long
)