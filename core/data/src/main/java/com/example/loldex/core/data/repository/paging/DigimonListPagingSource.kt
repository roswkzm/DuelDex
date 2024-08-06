package com.example.loldex.core.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.loldex.core.data.mapper.toData
import com.example.loldex.core.data.repository.DigimonRepository
import com.example.loldex.core.model.DigimonContentData
import kotlinx.coroutines.flow.first
import java.io.IOException

class DigimonListPagingSource(
    private val size: Int,
    private val digimonRepository: DigimonRepository
) : PagingSource<Int, DigimonContentData>() {
    override fun getRefreshKey(state: PagingState<Int, DigimonContentData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DigimonContentData> {
        return try {
            val currentPage = params.key ?: 0
            val response = digimonRepository.getDigimonList(currentPage, size)
            val totalPage = response.first().pageable.totalPages
            val digimonContentData = response.first().content.map {
                it.toData()
            }

            LoadResult.Page(
                data = digimonContentData,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage >= totalPage) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

}