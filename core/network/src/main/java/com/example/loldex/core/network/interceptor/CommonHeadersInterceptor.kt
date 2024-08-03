package com.example.loldex.core.network.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CommonHeadersInterceptor @Inject constructor(

) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("accept", "application/json;charset=UTF-8")
            .build()
        chain.proceed(newRequest)
    }
}