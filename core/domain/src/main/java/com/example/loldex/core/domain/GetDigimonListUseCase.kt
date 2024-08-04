package com.example.loldex.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.loldex.core.data.repository.DigimonRepository
import com.example.loldex.core.data.repository.paging.DigimonListPagingSource
import com.example.loldex.core.model.DigimonContentData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDigimonListUseCase @Inject constructor(
    private val digimonRepository: DigimonRepository,
) {
    operator fun invoke(
        pageSize: Int
    ): Flow<PagingData<DigimonContentData>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = {
                DigimonListPagingSource(
                    size = pageSize,
                    digimonRepository = digimonRepository,
                )
            }
        ).flow
    }
}