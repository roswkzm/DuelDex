package com.example.loldex.core.network.util

import com.example.loldex.core.network.model.DisneyDataResponse
import com.example.loldex.core.network.model.response.DisneyCharacterResponse
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

fun parseDisneyDataResponse(jsonElement: JsonElement): DisneyDataResponse<List<DisneyCharacterResponse>> {
    return try {
        val listResponse = Json.decodeFromJsonElement(
            DisneyDataResponse.serializer(ListSerializer(DisneyCharacterResponse.serializer())),
            jsonElement
        )
        DisneyDataResponse(listResponse.info, listResponse.data)
    } catch (e: Exception) {
        val singleResponse = Json.decodeFromJsonElement(
            DisneyDataResponse.serializer(DisneyCharacterResponse.serializer()),
            jsonElement
        )
        DisneyDataResponse(singleResponse.info, listOf(singleResponse.data))
    }
}