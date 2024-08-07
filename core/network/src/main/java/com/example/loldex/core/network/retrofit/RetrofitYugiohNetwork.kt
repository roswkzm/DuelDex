package com.example.loldex.core.network.retrofit

import androidx.tracing.trace
import com.example.loldex.core.network.YugiohNetworkDataSource
import com.example.loldex.core.network.model.response.CardListResponse
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
    suspend fun getYugiohList(
        @Query("num") num: Int,
        @Query("offset") offset: Int,
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

    override suspend fun getYugiohList(num: Int, offset: Int): ApiResponse<CardListResponse> =
        networkApi.getYugiohList(num = num, offset = offset)


}