package com.example.loldex.core.data.repository

import com.example.loldex.core.network.model.ListResponse
import com.example.loldex.core.network.model.response.DisneyCharacter
import kotlinx.coroutines.flow.Flow

interface DisneyRepository {

    fun getCharacter(
        page: Int,
        pageSize: Int,
    ): Flow<ListResponse<List<DisneyCharacter>>>

    fun getCharacterById(
        id: Int,
    ): Flow<DisneyCharacter>

    fun getCharacterByName(
        name: String
    ): Flow<List<DisneyCharacter>>
}