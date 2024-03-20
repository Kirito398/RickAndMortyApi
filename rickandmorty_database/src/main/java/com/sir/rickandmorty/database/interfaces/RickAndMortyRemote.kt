package com.sir.rickandmorty.database.interfaces

import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRemote {
    fun getCharacters(page: Int?): Flow<RequestResult<CharactersWithPaginationInfo>>
}