package com.sir.rickandmorty.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sir.rickandmorty.cache.room.daos.CharacterDao
import com.sir.rickandmorty.cache.room.entities.CharacterEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CharacterEntity::class]
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

fun createDataBase(applicationContext: Context): RickAndMortyDatabase {
    return Room.databaseBuilder(
        context = applicationContext.applicationContext,
        RickAndMortyDatabase::class.java,
        name = "RickAndMortyDataBase"
    ).build()
}