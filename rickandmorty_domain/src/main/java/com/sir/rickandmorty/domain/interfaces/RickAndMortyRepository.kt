package com.sir.rickandmorty.domain.interfaces

import com.sir.rickandmorty.domain.models.CharactersFilter
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    fun getCharacters(
        page: Int?,
        filter: CharactersFilter
    ): Flow<RequestResult<CharactersWithPaginationInfo>>
}