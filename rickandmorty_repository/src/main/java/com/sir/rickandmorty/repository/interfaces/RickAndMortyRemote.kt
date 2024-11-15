package com.sir.rickandmorty.repository.interfaces

import com.sir.rickandmorty.domain.models.CharactersFilter
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.repository.models.RequestResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRemote {
    fun getCharacters(
        page: Int?,
        filter: CharactersFilter,
    ): Flow<RequestResponse<CharactersWithPaginationInfo>>
}