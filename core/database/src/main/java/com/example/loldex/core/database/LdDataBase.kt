package com.example.loldex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.loldex.core.database.dao.DeckDao
import com.example.loldex.core.database.dao.YugiohCardDao
import com.example.loldex.core.database.model.DeckCardCrossRef
import com.example.loldex.core.database.model.DeckEntity
import com.example.loldex.core.database.model.YugiohCardEntity
import com.example.loldex.core.database.util.Converters

@Database(
    entities = [
        YugiohCardEntity::class,
        DeckEntity::class,
        DeckCardCrossRef::class
    ],
    version = 1
)

@TypeConverters(
    Converters::class,
)
internal abstract class LdDataBase : RoomDatabase() {
    abstract fun yugiohCardDao(): YugiohCardDao
    abstract fun deckDao(): DeckDao
}