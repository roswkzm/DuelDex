package com.example.loldex.core.network.retrofit

import androidx.tracing.trace
import com.example.loldex.core.network.DisneyNetworkDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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

private interface RetrofitDisneyNetworkApi {
    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    )

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int,
    )

    @GET("character")
    suspend fun getCharacterByName(
        @Query("name") name: String,
    )
}

@Singleton
class RetrofitDisneyNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : DisneyNetworkDataSource {

    private val baseUrl = "https://api.disneyapi.dev/"

    private val networkApi = trace("RetrofitDisneyNetwork") {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(RetrofitDisneyNetworkApi::class.java)
    }

    override suspend fun getCharacter(page: Int, pageSize: Int) {
        networkApi.getCharacter(
            page = page,
            pageSize = pageSize,
        )
    }

    override suspend fun getCharacterById(id: Int) {
        networkApi.getCharacterById(id)
    }

    override suspend fun getCharacterByName(name: String) {
        networkApi.getCharacterByName(name)
    }
}