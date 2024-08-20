package com.example.loldex.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.loldex.core.model.DeckWithCardData

data class DeckWithCards(
    @Embedded val deckEntity: DeckEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = DeckCardCrossRef::class,
            parentColumn = "deckId",
            entityColumn = "cardId"
        )
    )
    val cards: List<YugiohCardEntity>
)

fun DeckWithCards.asExternalModel() = DeckWithCardData(
    deckData = deckEntity.asExternalModel(),
    yugiohCardList = cards.map { it.asExternalModel() }
)