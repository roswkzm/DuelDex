package com.example.dueldex.core.database.util

import androidx.room.TypeConverter
import com.example.dueldex.core.model.YugiohCardImage
import com.example.dueldex.core.model.YugiohCardPrice
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json

    @TypeConverter
    fun fromCardImages(cardImages: List<YugiohCardImage>): String {
        return json.encodeToString(cardImages)
    }

    @TypeConverter
    fun toCardImages(cardImagesString: String): List<YugiohCardImage> {
        return json.decodeFromString(cardImagesString)
    }

    @TypeConverter
    fun fromCardPrices(cardPrices: List<YugiohCardPrice>): String {
        return json.encodeToString(cardPrices)
    }

    @TypeConverter
    fun toCardPrices(cardPricesString: String): List<YugiohCardPrice> {
        return json.decodeFromString(cardPricesString)
    }
}