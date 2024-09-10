package com.example.dueldex.core.network.retrofit

import androidx.tracing.trace
import com.example.dueldex.core.network.YugiohNetworkDataSource
import com.example.dueldex.core.network.model.response.CardListResponse
import com.example.dueldex.core.network.model.response.CardPagingListResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

private interface RetrofitYugiohNetworkApi {
    @GET("cardinfo.php")
    suspend fun getYugiohPagingList(
        @Query("num") num: Int,
        @Query("offset") offset: Int,
    ): ApiResponse<CardPagingListResponse>

    @GET("cardinfo.php")
    suspend fun getYugiohCardDataById(
        @Query("id") id: Long,
    ): ApiResponse<CardListResponse>

    @GET("cardinfo.php")
    suspend fun getYugiohCardDataByName(
        @Query("name") name: String,
    ): ApiResponse<CardListResponse>

    @GET("cardinfo.php")
    suspend fun getYugiohCardDataBySearchString(
        @Query("fname") searchString: String,
        @Query("type") cardType: String?,
        @Query("attribute") attribute: String?,
        @Query("race") race: String?,
        @Query("effect") effect: String?,
        @Query("level") level: Int?,
    ): ApiResponse<CardListResponse>
}

class RetrofitYugiohNetwork @Inject constructor(
    private val networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : YugiohNetworkDataSource {

    private val baseUrl = "https://db.ygoprodeck.com/api/v7/"

    private val networkApi = trace("RetrofitYugiohNetwork") {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(RetrofitYugiohNetworkApi::class.java)
    }

    override suspend fun getYugiohPagingList(
        num: Int,
        offset: Int
    ): ApiResponse<CardPagingListResponse> =
        networkApi.getYugiohPagingList(num = num, offset = offset)

    override suspend fun getYugiohCardDataById(id: Long): ApiResponse<CardListResponse> =
        networkApi.getYugiohCardDataById(id)

    override suspend fun getYugiohCardDataByName(name: String): ApiResponse<CardListResponse> =
        networkApi.getYugiohCardDataByName(name)

    override suspend fun getYugiohCardDataBySearchString(
        searchString: String,
        type: String?,
        attribute: String?,
        race: String?,
        effect: String?,
        level: Int?,
    ): ApiResponse<CardListResponse> =
        networkApi.getYugiohCardDataBySearchString(
            searchString = searchString,
            cardType = type,
            attribute = attribute,
            race = race,
            effect = effect,
            level = level,
        )
}