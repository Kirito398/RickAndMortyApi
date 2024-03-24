package com.sir.rickandmortyapi.di.module

import com.sir.entity.api.retrofit.RetrofitApiFactory
import com.sir.rickandmorty.api.RickAndMortyApi
import com.sir.rickandmorty.api.RickAndMortyRemoteImpl
import com.sir.rickandmorty.api.utils.ErrorParser
import com.sir.rickandmorty.database.interfaces.RickAndMortyRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDomain {

    @Provides
    fun provideRickAndMortyRemote(api: RickAndMortyApi, errorParser: ErrorParser): RickAndMortyRemote {
        return RickAndMortyRemoteImpl(
            api = api,
            errorParser = errorParser
        )
    }

    @Provides
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return RetrofitApiFactory.create(
            baseUrl = "https://rickandmortyapi.com/api/",
            isDebug = true
        )
    }

    @Provides
    fun provideErrorParser(): ErrorParser {
        return ErrorParser()
    }
}