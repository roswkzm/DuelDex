package com.example.dueldex.core.database.util

import androidx.room.TypeConverter
import com.example.dueldex.core.model.YugiohCardImage
import com.example.dueldex.core.model.YugiohCardPrice
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCardImages(cardImages: List<YugiohCardImage>): String {
        return gson.toJson(cardImages)
    }

    @TypeConverter
    fun toCardImages(cardImagesString: String): List<YugiohCardImage> {
        val listType = object : TypeToken<List<YugiohCardImage>>() {}.type
        return gson.fromJson(cardImagesString, listType)
    }

    @TypeConverter
    fun fromCardPrices(cardPrices: List<YugiohCardPrice>): String {
        return gson.toJson(cardPrices)
    }

    @TypeConverter
    fun toCardPrices(cardPricesString: String): List<YugiohCardPrice> {
        val listType = object : TypeToken<List<YugiohCardPrice>>() {}.type
        return gson.fromJson(cardPricesString, listType)
    }
}