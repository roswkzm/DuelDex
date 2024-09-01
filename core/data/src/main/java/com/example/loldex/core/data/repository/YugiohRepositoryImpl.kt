package com.example.loldex.core.data.repository

import com.example.loldex.core.common.network.Dispatcher
import com.example.loldex.core.common.network.LdDispatchers
import com.example.loldex.core.data.mapper.toData
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.network.YugiohNetworkDataSource
import com.example.loldex.core.network.model.mapper.ErrorResponseMapper
import com.example.loldex.core.network.model.response.CardPagingListResponse
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class YugiohRepositoryImpl @Inject constructor(
    @Dispatcher(LdDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: YugiohNetworkDataSource
) : YugiohRepository {

    override fun getYugiohPagingList(
        num: Int,
        offset: Int
    ): Flow<CardPagingListResponse> =
        flow {
            val response = dataSource.getYugiohPagingList(num, offset)
            response.suspendOnSuccess {
                emit(data)
            }.onError {

            }.onException {

            }
        }.flowOn(ioDispatcher)

    override fun getYugiohCardDataById(
        id: Long
    ): Flow<YugiohCardData> =
        flow {
            val response = dataSource.getYugiohCardDataById(id)
            response.suspendOnSuccess {
                emit(data.data[0].toData())
            }.onError {
                map(ErrorResponseMapper) { Timber.e("[Error] : $error") }
            }.onException {
                Timber.e(message)
            }
        }.flowOn(ioDispatcher)

    override fun getYugiohCardDataByName(
        name: String
    ): Flow<YugiohCardData> =
        flow {
            val response = dataSource.getYugiohCardDataByName(name)
            response.suspendOnSuccess {
                emit(data.data[0].toData())
            }.onError {
                map(ErrorResponseMapper) { Timber.e("[Error] : $error") }
            }.onException {
                Timber.e(message)
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
    ): Flow<List<YugiohCardData>> =
        flow {
            val response = dataSource.getYugiohCardDataBySearchString(
                searchString = searchString,
                type = type,
                attribute = attribute,
                race = race,
                level = level,
                effect = effect,
            )
            response.suspendOnSuccess {
                emit(data.data.toData())
            }.onError {
                map(ErrorResponseMapper) { onError("[Code: $code]: $error") }
            }.onException {
                Timber.e(message)
            }
        }.flowOn(ioDispatcher)
}