package com.sir.rickandmorty.repository

import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.interfaces.RickAndMortyRepository
import com.sir.rickandmorty.domain.models.CharactersFilter
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache
import com.sir.rickandmorty.repository.models.RequestResponse
import com.sir.rickandmorty.repository.utils.RequestResultMergeStrategy
import com.sir.rickandmorty.repository.utils.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.flow

class RickAndMortyRepositoryImpl(
    private val remote: RickAndMortyRemote,
    private val cache: RickAndMortyCache,
    private val mergeStrategy: RequestResultMergeStrategy
) : RickAndMortyRepository {

    override fun getCharacters(
        page: Int?,
        filter: CharactersFilter,
    ): Flow<RequestResult<CharactersWithPaginationInfo>> {

        val cachedData = flowOf { cache.getCharacters(page = page ?: 1, filter = filter) }
            .mapToDomain()
            .startFromProgress()

        val remoteData = remote.getCharacters(
            page = page,
            filter = filter
        )
        .onEach {
            if (it is RequestResponse.Success) {
                cache.updateCharacters(it.data.results)
            }
        }
        .mapToDomain()
        .startFromProgress()

        return remoteData.combine(cachedData, mergeStrategy::merge)
    }

    private fun <R> Flow<RequestResult<R>>.startFromProgress(): Flow<RequestResult<R>> {
        val start = flowOf(RequestResult.InProgress<R>())
        return merge(start, this)
    }

    private fun <T> flowOf(function: suspend () -> T): Flow<T> {
        return flow {
            emit(function())
        }
    }
}
