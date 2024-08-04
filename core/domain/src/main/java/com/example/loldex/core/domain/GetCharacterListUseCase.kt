package com.example.loldex.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.loldex.core.data.repository.DisneyRepository
import com.example.loldex.core.data.repository.paging.CharacterListPagingSource
import com.example.loldex.core.model.DisneyCharacterData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val disneyRepository: DisneyRepository,
) {
    operator fun invoke(
        pageSize: Int
    ): Flow<PagingData<DisneyCharacterData>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = {
                CharacterListPagingSource(
                    size = pageSize,
                    disneyRepository = disneyRepository,
                )
            }
        ).flow
    }
}