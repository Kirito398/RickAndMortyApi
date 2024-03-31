package com.sir.rickandmorty.cache

import com.sir.rickandmorty.cache.room.RickAndMortyDatabase
import com.sir.rickandmorty.cache.utils.mapToCacheModel
import com.sir.rickandmorty.cache.utils.mapToRepositoryModel
import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import com.sir.rickandmorty.repository.models.RequestResponse
import com.sir.rickandmorty.repository.models.wrapToRequestResponse

class RickAndMortyCacheImpl(
    private val roomDatabase: RickAndMortyDatabase
) : RickAndMortyCache {
    override suspend fun getCharacters(): RequestResponse<CharactersWithPaginationInfo> {
        val result = roomDatabase.characterDao.getAll().map {
            it.mapToRepositoryModel()
        }

        return CharactersWithPaginationInfo(
            info = CharactersWithPaginationInfo.PaginationInfo(
                count = result.size,
                pages = 1
            ),
            results = result
        ).wrapToRequestResponse()
    }

    override suspend fun putCharacters(characters: List<CharacterInfo>) {
        roomDatabase.characterDao.insert(
            characters.map { it.mapToCacheModel() }
        )
    }

    override suspend fun updateCharacters(characters: List<CharacterInfo>) {
        roomDatabase.characterDao.update(
            characters.map { it.mapToCacheModel() }
        )
    }
}