package com.sir.rickandmorty.cache

import com.sir.rickandmorty.cache.room.RickAndMortyDatabase
import com.sir.rickandmorty.cache.utils.mapToCacheModel
import com.sir.rickandmorty.cache.utils.mapToRepositoryModel
import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersFilter
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import com.sir.rickandmorty.repository.models.RequestResponse
import com.sir.rickandmorty.repository.models.wrapToRequestResponse

class RickAndMortyCacheImpl(
    private val roomDatabase: RickAndMortyDatabase
) : RickAndMortyCache {
    companion object {
        private const val COUNTS_IN_PAGE = 20
    }

    override suspend fun getCharacters(
        page: Int,
        filter: CharactersFilter
    ): RequestResponse<CharactersWithPaginationInfo> {
        val result = roomDatabase.characterDao.getAll()
            .filter { it.name.contains(filter.name) }
            .map { it.mapToRepositoryModel() }

        return CharactersWithPaginationInfo(
            info = CharactersWithPaginationInfo.PaginationInfo(
                count = result.size,
                pages = result.size / COUNTS_IN_PAGE
            ),
            results = result.drop((page - 1) * COUNTS_IN_PAGE).take(COUNTS_IN_PAGE)
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