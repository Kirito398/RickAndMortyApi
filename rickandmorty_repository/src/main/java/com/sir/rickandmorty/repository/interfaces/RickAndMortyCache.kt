package com.sir.rickandmorty.repository.interfaces

import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.repository.models.RequestResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyCache {
    fun getCharacters(): Flow<RequestResponse<CharactersWithPaginationInfo>>

    suspend fun putCharacters(characters: List<CharacterInfo>)
}