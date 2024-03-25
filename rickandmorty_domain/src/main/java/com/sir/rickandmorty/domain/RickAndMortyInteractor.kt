package com.sir.rickandmorty.domain

import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import kotlinx.coroutines.flow.Flow

class RickAndMortyInteractor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    fun getCharactersList(page: Int? = null): Flow<RequestResult<CharactersWithPaginationInfo>> {
        return rickAndMortyRepository.getCharacters(page = page)
    }
}