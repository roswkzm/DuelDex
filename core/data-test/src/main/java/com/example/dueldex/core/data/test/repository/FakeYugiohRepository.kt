package com.example.dueldex.core.data.test.repository

import com.example.dueldex.core.common.network.Dispatcher
import com.example.dueldex.core.common.network.LdDispatchers.IO
import com.example.dueldex.core.data.mapper.toData
import com.example.dueldex.core.data.repository.YugiohRepository
import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.network.demo.DemoYugiohNetworkDataSource
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class FakeYugiohRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: DemoYugiohNetworkDataSource
) : YugiohRepository {
    override fun getYugiohPagingList(num: Int, offset: Int) = flow {
        val response = dataSource.getYugiohPagingList(num, offset)
        if (response is ApiResponse.Success) {
            emit(response.data)
        } else {
            throw IllegalStateException("Error fetching paging list")
        }
    }.flowOn(ioDispatcher)

    override fun getYugiohCardDataById(id: Long): Flow<YugiohCardData> = flow {
        val response = dataSource.getYugiohCardDataById(id)
        if (response is ApiResponse.Success) {
            emit(response.data.data.first().toData())
        } else {
            throw IllegalStateException("Error fetching card by ID")
        }
    }.flowOn(ioDispatcher)

    override fun getYugiohCardDataByName(name: String): Flow<YugiohCardData> = flow {
        val response = dataSource.getYugiohCardDataByName(name)
        if (response is ApiResponse.Success) {
            emit(response.data.data.first().toData())
        } else {
            throw IllegalStateException("Error fetching card by name")
        }
    }.flowOn(ioDispatcher)

    override fun getYugiohCardDataBySearchString(
        searchString: String,
        type: String?,
        attribute: String?,
        race: String?,
        effect: String?,
        level: Int?,
        onError: (String) -> Unit
    ): Flow<List<YugiohCardData>> = flow {
        val response = dataSource.getYugiohCardDataBySearchString(
            searchString, type, attribute, race, effect, level
        )
        if (response is ApiResponse.Success) {
            emit(response.data.data.map { it.toData() })
        } else {
            onError("Error fetching cards by search string")
        }
    }.flowOn(ioDispatcher)
}