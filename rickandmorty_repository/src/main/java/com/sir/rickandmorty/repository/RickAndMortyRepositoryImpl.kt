package com.sir.rickandmorty.repository

import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import kotlinx.coroutines.flow.Flow

class RickAndMortyRepositoryImpl(
    private val remote: RickAndMortyRemote
) : RickAndMortyRepository {

    override fun getCharacters(page: Int?): Flow<RequestResult<CharactersWithPaginationInfo>> {
        return remote.getCharacters(page = page)
    }
}