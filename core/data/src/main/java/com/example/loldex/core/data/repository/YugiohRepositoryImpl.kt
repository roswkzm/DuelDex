package com.example.loldex.core.data.repository

import com.example.loldex.core.common.network.Dispatcher
import com.example.loldex.core.common.network.LdDispatchers
import com.example.loldex.core.network.YugiohNetworkDataSource
import com.example.loldex.core.network.model.response.CardListResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class YugiohRepositoryImpl @Inject constructor(
    @Dispatcher(LdDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: YugiohNetworkDataSource
) : YugiohRepository {

    override fun getYugiohCardList(
        num: Int,
        offset: Int
    ): Flow<CardListResponse> =
        flow {
            val response = dataSource.getYugiohList(num, offset)
            response.suspendOnSuccess {
                emit(data)
            }.onError {

            }.onException {

            }
        }.flowOn(ioDispatcher)
}