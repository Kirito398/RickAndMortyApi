package com.sir.rickandmorty.cache.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sir.rickandmorty.cache.room.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM characters")
    fun observeAll(): Flow<List<CharacterEntity>>
    @Insert
    suspend fun insert(list: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(list: List<CharacterEntity>)

    @Delete
    suspend fun delete(list: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clean()
}