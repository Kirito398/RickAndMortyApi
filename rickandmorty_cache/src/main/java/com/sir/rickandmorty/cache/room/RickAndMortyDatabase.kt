package com.sir.rickandmorty.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sir.rickandmorty.cache.room.daos.CharacterDao
import com.sir.rickandmorty.cache.room.entities.CharacterEntity
import com.sir.rickandmorty.cache.room.entities.converters.DateTimeTypeConverter
import com.sir.rickandmorty.cache.room.entities.converters.StringListTypeConverter

@Database(
    version = 1,
    exportSchema = false,
    entities = [CharacterEntity::class]
)
@TypeConverters(
    DateTimeTypeConverter::class,
    StringListTypeConverter::class,
)
abstract class RickAndMortyRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        fun createDataBase(applicationContext: Context): RickAndMortyDatabase {
            val roomDatabase = Room.databaseBuilder(
                context = applicationContext.applicationContext,
                RickAndMortyRoomDatabase::class.java,
                name = "RickAndMortyDataBase"
            ).build()

            return RickAndMortyDatabase(database = roomDatabase)
        }
    }
}

class RickAndMortyDatabase internal constructor(private val database: RickAndMortyRoomDatabase) {
    val characterDao: CharacterDao
        get() = database.characterDao()
}