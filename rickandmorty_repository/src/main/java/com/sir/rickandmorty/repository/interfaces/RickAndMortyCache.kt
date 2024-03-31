package com.sir.rickandmorty.repository.interfaces

import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.repository.models.RequestResponse

interface RickAndMortyCache {
    suspend fun getCharacters(): RequestResponse<CharactersWithPaginationInfo>
    suspend fun putCharacters(characters: List<CharacterInfo>)
    suspend fun updateCharacters(characters: List<CharacterInfo>)
}