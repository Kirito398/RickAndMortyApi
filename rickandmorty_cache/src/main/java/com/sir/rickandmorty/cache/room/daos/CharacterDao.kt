package com.sir.rickandmorty.cache.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getAll(): Flow<List<CharacterDao>>

    @Query("SELECT * FROM characters")
    fun observeAll(): Flow<List<CharacterDao>>
    @Insert
    suspend fun insert(list: List<CharacterDao>)

    @Delete
    suspend fun delete(list: List<CharacterDao>)

    @Query("DELETE FROM characters")
    suspend fun clean()
}