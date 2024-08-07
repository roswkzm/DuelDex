package com.example.loldex.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.loldex.core.data.repository.YugiohRepository
import com.example.loldex.core.data.repository.paging.YugiohPagingSource
import com.example.loldex.core.model.yugioh.YugiohCardData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYugiohListUseCase @Inject constructor(
    private val yugiohRepository: YugiohRepository,
) {
    operator fun invoke(
        num: Int
    ): Flow<PagingData<YugiohCardData>> {
        return Pager(
            config = PagingConfig(pageSize = num, prefetchDistance = num * 2),
            pagingSourceFactory = {
                YugiohPagingSource(
                    num = num,
                    yugiohRepository = yugiohRepository,
                )
            }
        ).flow
    }
}