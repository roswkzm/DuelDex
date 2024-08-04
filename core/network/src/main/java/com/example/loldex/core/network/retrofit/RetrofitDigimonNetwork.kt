package com.example.loldex.core.network.retrofit

import androidx.tracing.trace
import com.example.loldex.core.network.DigimonNetworkDataSource
import com.example.loldex.core.network.model.response.DigimonDetailResponse
import com.example.loldex.core.network.model.response.DigimonPagingResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitDigimonNetworkApi {

    @GET("digimon")
    suspend fun getDigimonList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): ApiResponse<DigimonPagingResponse>

    @GET("digimon/{id}")
    suspend fun getDigimonById(
        @Path("id") id: Int,
    ): ApiResponse<DigimonDetailResponse>

    @GET("digimon/{name}")
    suspend fun getDigimonByName(
        @Path("name") name: String,
    ): ApiResponse<DigimonDetailResponse>
}

@Singleton
class RetrofitDigimonNetwork @Inject constructor(
    private val networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : DigimonNetworkDataSource {

    private val baseUrl = "https://digi-api.com/api/v1/"

    private val networkApi = trace("RetrofitDigimonNetwork") {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(RetrofitDigimonNetworkApi::class.java)
    }

    override suspend fun getDigimonList(
        page: Int,
        pageSize: Int
    ): ApiResponse<DigimonPagingResponse> =
        networkApi.getDigimonList(
            page = page,
            pageSize = pageSize,
        )

    override suspend fun getDigimonById(
        id: Int
    ): ApiResponse<DigimonDetailResponse> =
        networkApi.getDigimonById(id)

    override suspend fun getDigimonByName(
        name: String
    ): ApiResponse<DigimonDetailResponse> =
        networkApi.getDigimonByName(name)
}