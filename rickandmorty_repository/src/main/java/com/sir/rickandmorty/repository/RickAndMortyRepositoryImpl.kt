package com.sir.rickandmorty.repository

import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResponse
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import kotlinx.coroutines.flow.Flow

class RickAndMortyRepositoryImpl(
    private val remote: RickAndMortyRemote,
    private val cache: RickAndMortyCache
) : RickAndMortyRepository {

    override fun getCharacters(page: Int?): Flow<RequestResponse<CharactersWithPaginationInfo>> {
        return remote.getCharacters(page = page)
    }
}