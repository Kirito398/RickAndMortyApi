package com.sir.rickandmorty.cache

import com.sir.rickandmorty.cache.room.RickAndMortyDatabase
import com.sir.rickandmorty.cache.utils.mapToCacheModel
import com.sir.rickandmorty.cache.utils.mapToRepositoryModel
import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import com.sir.rickandmorty.repository.models.RequestResponse
import com.sir.rickandmorty.repository.models.wrapToRequestResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickAndMortyCacheImpl(
    private val roomDatabase: RickAndMortyDatabase
) : RickAndMortyCache {
    override fun getCharacters(): Flow<RequestResponse<CharactersWithPaginationInfo>> {
        return roomDatabase.characterDao.observeAll().map { result ->
            CharactersWithPaginationInfo(
                info = CharactersWithPaginationInfo.PaginationInfo(
                    count = result.size,
                    pages = 1
                ),
                results = result.map { it.mapToRepositoryModel() }
            ).wrapToRequestResponse()
        }
    }

    override suspend fun putCharacters(characters: List<CharacterInfo>) {
        roomDatabase.characterDao.insert(
            characters.map { it.mapToCacheModel() }
        )
    }
}