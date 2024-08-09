package com.example.loldex.core.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.loldex.core.data.mapper.toData
import com.example.loldex.core.data.repository.YugiohRepository
import com.example.loldex.core.model.YugiohCardData
import kotlinx.coroutines.flow.first
import java.io.IOException

class YugiohPagingSource(
    private val num: Int,
    private val yugiohRepository: YugiohRepository
) : PagingSource<Int, YugiohCardData>() {
    override fun getRefreshKey(state: PagingState<Int, YugiohCardData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(num) ?: anchorPage?.nextKey?.minus(num)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, YugiohCardData> {
        return try {
            val offset = params.key ?: 0

            val response = yugiohRepository.getYugiohPagingList(num, offset).first()
            val totalRows = response.meta.totalRows
            val data = response.data.map { it.toData() }

            val nextKey = if (offset + num < totalRows) {
                offset + num
            } else if (offset + num == totalRows) {     // 딱맞아 떨어질경우
                null
            } else {    // 나머지 개수가 있을경우
                totalRows - offset
            }
            LoadResult.Page(
                data = data,
                prevKey = if (offset == 0) null else offset - num,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}