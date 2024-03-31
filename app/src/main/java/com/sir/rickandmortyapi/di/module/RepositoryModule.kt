package com.sir.rickandmortyapi.di.module

import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import com.sir.rickandmorty.repository.RickAndMortyRepositoryImpl
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.repository.utils.RequestResultMergeStrategy
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
    fun provideRickAndMortyRepository(
        remote: RickAndMortyRemote,
        cache: RickAndMortyCache
    ): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(
            remote = remote,
            cache = cache,
            mergeStrategy = RequestResultMergeStrategy()
        )
    }
}