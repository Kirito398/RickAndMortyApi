package com.sir.rickandmorty.domain.interfaces

import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    fun getCharacters(page: Int?): Flow<RequestResponse<CharactersWithPaginationInfo>>
}