package com.example.loldex.core.data.repository

import com.example.loldex.core.model.DisneyCharacterData
import com.example.loldex.core.network.model.ListResponse
import com.example.loldex.core.network.model.response.DisneyCharacterResponse
import kotlinx.coroutines.flow.Flow

interface DisneyRepository {

    fun getCharacter(
        page: Int,
        pageSize: Int,
    ): Flow<ListResponse<List<DisneyCharacterResponse>>>

    fun getCharacterById(
        id: Int,
    ): Flow<DisneyCharacterData>

    fun getCharacterByName(
        name: String
    ): Flow<List<DisneyCharacterData>>
}