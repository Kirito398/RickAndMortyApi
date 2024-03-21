package com.sir.entity.api.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitApiFactory {
    inline fun <reified API> create(
        baseUrl: String,
        isDebug: Boolean = false,
        okHttpClient: OkHttpClient = makeOkHttpClient(isDebug),
        converterFactory: Converter.Factory = GsonConverterFactory.create(),
        callAdapterFactory: CallAdapter.Factory = CallAdapterFactory(),
    ): API {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(API::class.java)
    }

    fun makeOkHttpClient(
        isDebug: Boolean = false,
        connectTimeOut: Long = 120,
        readTimeOut: Long = 120,
        connectTimeUnit: TimeUnit = TimeUnit.SECONDS,
        readTimeUnit: TimeUnit = TimeUnit.SECONDS,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeHttpLoggingInterceptor(isDebug))
            .connectTimeout(connectTimeOut, connectTimeUnit)
            .readTimeout(readTimeOut, readTimeUnit)
            .build()
    }

    fun makeHttpLoggingInterceptor(isDebug: Boolean = false): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when (isDebug) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}