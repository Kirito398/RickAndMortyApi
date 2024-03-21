package com.sir.rickandmorty.api

import com.sir.entity.api.retrofit.ResponseResult
import com.sir.rickandmorty.api.models.CharactersResponse
import com.sir.rickandmorty.api.utils.ErrorParser
import com.sir.rickandmorty.api.utils.mapToDomainFormat
import com.sir.rickandmorty.database.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.domain.models.type.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickAndMortyRemoteImpl(
    private val api: RickAndMortyApi,
    private val errorParser: ErrorParser,
) : RickAndMortyRemote {
    override fun getCharacters(page: Int?): Flow<RequestResult<CharactersWithPaginationInfo>> {
        return api.getAll(page = page)
            .runFlow()
            .mapToRequestResult(CharactersResponse::mapToDomainFormat)
    }

    private fun <R, T> Flow<ResponseResult<R>>.mapToRequestResult(
        mapToDomainFormat: R.() -> T
    ): Flow<RequestResult<T>> {
        return this.map {
            when (it) {
                is ResponseResult.Success.Empty -> RequestResult.Error(errorParser.parse(it))
                is ResponseResult.Success.HttpResponse -> RequestResult.Success(it.value.mapToDomainFormat())
                is ResponseResult.Failure.Error -> RequestResult.Error(Failure.ThrowableError(throwable = it.error))
                is ResponseResult.Failure.HttpError -> RequestResult.Error(errorParser.parse(it.exception))
            }
        }
    }
}