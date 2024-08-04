package com.example.loldex.core.data.repository

import com.example.loldex.core.common.network.Dispatcher
import com.example.loldex.core.common.network.LdDispatchers
import com.example.loldex.core.network.DigimonNetworkDataSource
import com.example.loldex.core.network.model.response.DigimonDetailResponse
import com.example.loldex.core.network.model.response.DigimonPagingResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DigimonRepositoryImpl @Inject constructor(
    @Dispatcher(LdDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: DigimonNetworkDataSource
) : DigimonRepository {

    override fun getDigimonList(
        page: Int,
        pageSize: Int
    ): Flow<DigimonPagingResponse> =
        flow {
            val response = dataSource.getDigimonList(page, pageSize)
            response.suspendOnSuccess {
                emit(data)
            }.onError {

            }.onException {

            }
        }.flowOn(ioDispatcher)

    override fun getDigimonById(
        id: Int
    ): Flow<DigimonDetailResponse> =
        flow {
            val response = dataSource.getDigimonById(id)
            response.suspendOnSuccess {
                emit(data)
            }.onError {

            }.onException {

            }
        }.flowOn(ioDispatcher)

    override fun getDigimonByName(
        name: String
    ): Flow<DigimonDetailResponse> =
        flow {
            val response = dataSource.getDigimonByName(name)
            response.suspendOnSuccess {
                emit(data)
            }.onError {

            }.onException {

            }
        }.flowOn(ioDispatcher)
}