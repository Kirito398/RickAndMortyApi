package com.sir.rickandmortyapi.di.module

import com.sir.rickandmorty.domain.RickAndMortyInteractor
import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class DomainModule {

    @Provides
    fun provideRickAndMortyInteractor(repository: RickAndMortyRepository): RickAndMortyInteractor {
        return RickAndMortyInteractor(repository)
    }
}