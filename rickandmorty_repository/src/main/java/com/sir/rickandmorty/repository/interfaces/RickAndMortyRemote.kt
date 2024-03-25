package com.sir.rickandmorty.repository.interfaces

import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRemote {
    fun getCharacters(page: Int?): Flow<RequestResponse<CharactersWithPaginationInfo>>
}