package com.example.dueldex.core.network.demo

import JvmUnitTestDemoAssetManager
import com.example.dueldex.core.common.network.Dispatcher
import com.example.dueldex.core.common.network.LdDispatchers.IO
import com.example.dueldex.core.network.YugiohNetworkDataSource
import com.example.dueldex.core.network.model.response.CardListResponse
import com.example.dueldex.core.network.model.response.CardPagingListResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class DemoYugiohNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : YugiohNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getYugiohPagingList(
        num: Int,
        offset: Int
    ): ApiResponse<CardPagingListResponse> =
        withContext(ioDispatcher) {
            val response: CardPagingListResponse =
                assets.open(YUGIOH_PAGING_LIST_ASSET).use(networkJson::decodeFromStream)
            ApiResponse.Success(response)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getYugiohCardDataById(id: Long): ApiResponse<CardListResponse> =
        withContext(ioDispatcher) {
            val response: CardListResponse =
                assets.open(YUGIOH_CARD_LIST_ASSET).use(networkJson::decodeFromStream)
            ApiResponse.Success(response)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getYugiohCardDataByName(name: String): ApiResponse<CardListResponse> =
        withContext(ioDispatcher) {
            val response: CardListResponse =
                assets.open(YUGIOH_CARD_LIST_ASSET).use(networkJson::decodeFromStream)
            ApiResponse.Success(response)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getYugiohCardDataBySearchString(
        searchString: String,
        type: String?,
        attribute: String?,
        race: String?,
        effect: String?,
        level: Int?
    ): ApiResponse<CardListResponse> =
        withContext(ioDispatcher) {
            val response: CardListResponse =
                assets.open(YUGIOH_CARD_LIST_ASSET).use(networkJson::decodeFromStream)
            ApiResponse.Success(response)
        }

    companion object {
        private const val YUGIOH_PAGING_LIST_ASSET = "yugioh_paging_list.json"
        private const val YUGIOH_CARD_LIST_ASSET = "yugioh_card_list.json"
    }
}

