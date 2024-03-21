package com.sir.rickandmorty.api

import com.sir.entity.api.retrofit.CallResponse
import com.sir.rickandmorty.api.models.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    fun getAll(@Query("page") page: Int?): CallResponse<CharactersResponse>
}