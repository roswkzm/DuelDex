package com.example.dueldex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dueldex.core.database.dao.DeckDao
import com.example.dueldex.core.database.dao.YugiohCardDao
import com.example.dueldex.core.database.model.DeckCardCrossRef
import com.example.dueldex.core.database.model.DeckEntity
import com.example.dueldex.core.database.model.YugiohCardEntity
import com.example.dueldex.core.database.util.Converters

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
internal abstract class DdDataBase : RoomDatabase() {
    abstract fun yugiohCardDao(): YugiohCardDao
    abstract fun deckDao(): DeckDao
}