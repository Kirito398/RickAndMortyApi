package com.sir.rickandmortyapi.di.module

import com.sir.rickandmorty.database.RickAndMortyRepositoryImpl
import com.sir.rickandmorty.database.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(remote: RickAndMortyRemote): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(remote)
    }
}