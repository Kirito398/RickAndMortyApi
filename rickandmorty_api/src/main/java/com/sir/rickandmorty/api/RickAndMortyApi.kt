package com.sir.rickandmorty.api

import com.sir.rickandmorty.api.models.CharactersResponse
import com.sir.rickandmorty.api.retrofit.CallResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    fun getAll(@Query("page") page: Int?): CallResponse<CharactersResponse>
}