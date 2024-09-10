package com.example.dueldex.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dueldex.core.data.repository.YugiohRepository
import com.example.dueldex.core.data.repository.paging.YugiohPagingSource
import com.example.dueldex.core.model.YugiohCardData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYugiohListUseCase @Inject constructor(
    private val yugiohRepository: YugiohRepository,
) {
    operator fun invoke(
        num: Int
    ): Flow<PagingData<YugiohCardData>> {
        return Pager(
            config = PagingConfig(pageSize = num, prefetchDistance = num),
            pagingSourceFactory = {
                YugiohPagingSource(
                    num = num,
                    yugiohRepository = yugiohRepository,
                )
            }
        ).flow
    }
}