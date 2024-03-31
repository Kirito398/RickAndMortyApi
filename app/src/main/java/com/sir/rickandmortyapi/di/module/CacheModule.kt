package com.sir.rickandmortyapi.di.module

import android.content.Context
import com.sir.rickandmorty.cache.RickAndMortyCacheImpl
import com.sir.rickandmorty.cache.room.RickAndMortyDatabase
import com.sir.rickandmorty.cache.room.RickAndMortyRoomDatabase
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    fun provideRickAndMortyCache(database: RickAndMortyDatabase): RickAndMortyCache {
        return RickAndMortyCacheImpl(database)
    }

    @Singleton
    @Provides
    fun provideRickAndMortyDatabase(@ApplicationContext context: Context): RickAndMortyDatabase {
        return RickAndMortyRoomDatabase.createDataBase(context)
    }
}