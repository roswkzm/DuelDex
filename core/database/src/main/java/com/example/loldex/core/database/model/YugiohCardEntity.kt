package com.example.loldex.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.model.YugiohCardImage
import com.example.loldex.core.model.YugiohCardPrice

@Entity(
    tableName = "yugioh_card_data",
)
data class YugiohCardEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val type: String,
    @ColumnInfo(name = "frame_type")
    val frameType: String,
    val desc: String,
    val atk: Int? = null,
    val def: Int? = null,
    val level: Int? = null,
    val race: String,
    val attribute: String? = null,
    val archetype: String? = null,
    @ColumnInfo(name = "ygoprodeck_url")
    val ygoprodeckUrl: String,
    @ColumnInfo(name = "card_images")
    val cardImages: List<YugiohCardImage>,
    @ColumnInfo(name = "card_prices")
    val cardPrices: List<YugiohCardPrice>
)

fun YugiohCardEntity.asExternalModel() = YugiohCardData(
    id = id,
    name = name,
    type = type,
    frameType = frameType,
    desc = desc,
    atk = atk,
    def = def,
    level = level,
    race = race,
    attribute = attribute,
    archetype = archetype,
    ygoprodeckUrl = ygoprodeckUrl,
    cardImages = cardImages,
    cardPrices = cardPrices
)

fun YugiohCardData.asEntity() = YugiohCardEntity(
    id = id,
    name = name,
    type = type,
    frameType = frameType,
    desc = desc,
    atk = atk,
    def = def,
    level = level,
    race = race,
    attribute = attribute,
    archetype = archetype,
    ygoprodeckUrl = ygoprodeckUrl,
    cardImages = cardImages,
    cardPrices = cardPrices
)