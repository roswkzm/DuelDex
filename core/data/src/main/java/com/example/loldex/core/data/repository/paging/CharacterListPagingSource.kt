package com.example.loldex.core.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.loldex.core.data.mapper.toData
import com.example.loldex.core.data.repository.DisneyRepository
import com.example.loldex.core.model.DisneyCharacterData
import kotlinx.coroutines.flow.first
import java.io.IOException

class CharacterListPagingSource(
    private val size: Int,
    private val disneyRepository: DisneyRepository
) : PagingSource<Int, DisneyCharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, DisneyCharacterData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DisneyCharacterData> {
        return try {
            val currentPage = params.key ?: 1
            val response = disneyRepository.getCharacter(currentPage, size)
            val totalPage = response.first().info.totalPages!!
            val disneyCharacterData = response.first().data.map {
                it.toData()
            }

            LoadResult.Page(
                data = disneyCharacterData,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage >= totalPage) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

}