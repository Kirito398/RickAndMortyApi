package com.sir.rickandmorty.api

import com.sir.rickandmorty.api.models.CharactersResponse
import com.sir.rickandmorty.api.retrofit.CallAdapterFactory
import com.sir.rickandmorty.api.retrofit.CallResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    fun getAll(@Query("page") page: Int?): CallResponse<CharactersResponse>
}

fun RickAndMortyApi(
    baseUrl: String,
    //okHttpClient: OkHttpClient = OkHttpClient(),
): RickAndMortyApi {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CallAdapterFactory())
        .client(okHttpClient)
        .build()
        .create(RickAndMortyApi::class.java)
}