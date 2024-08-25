package com.example.loldex.core.model

import kotlinx.serialization.Serializable

@Serializable
data class DeckData(
    val id: Long = 0,
    val deckName: String
)
