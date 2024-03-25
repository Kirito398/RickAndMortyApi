package com.sir.rickandmorty.repository

import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import kotlinx.coroutines.flow.Flow

class RickAndMortyRepositoryImpl(
    private val remote: RickAndMortyRemote,
    private val cache: RickAndMortyCache
) : RickAndMortyRepository {

    override fun getCharacters(page: Int?): Flow<RequestResult<CharactersWithPaginationInfo>> {
        TODO()
        //return remote.getCharacters(page = page)
    }
}