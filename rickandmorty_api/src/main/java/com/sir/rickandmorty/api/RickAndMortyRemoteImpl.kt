package com.sir.rickandmorty.api

import com.sir.entity.api.retrofit.ResponseResult
import com.sir.rickandmorty.api.models.CharactersResponse
import com.sir.rickandmorty.api.utils.ErrorParser
import com.sir.rickandmorty.api.utils.mapToDomainFormat
import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.base.RequestResponse
import com.sir.rickandmorty.domain.models.base.wrapToRequestResult
import com.sir.rickandmorty.domain.models.type.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickAndMortyRemoteImpl(
    private val api: RickAndMortyApi,
    private val errorParser: ErrorParser,
) : RickAndMortyRemote {
    override fun getCharacters(page: Int?): Flow<RequestResponse<CharactersWithPaginationInfo>> {
        return api.getAll(page = page).runFlow()
            .mapToRequestResult(CharactersResponse::mapToDomainFormat)
    }

    private fun <R, T> Flow<ResponseResult<R>>.mapToRequestResult(
        mapToDomainFormat: R.() -> T
    ): Flow<RequestResponse<T>> {
        return this.map {
            when (it) {
                is ResponseResult.Success.Empty -> errorParser.parse(it).wrapToRequestResult()
                is ResponseResult.Success.HttpResponse -> it.value.mapToDomainFormat().wrapToRequestResult()
                is ResponseResult.Failure.Error -> Failure.ThrowableError(throwable = it.error).wrapToRequestResult()
                is ResponseResult.Failure.HttpError -> errorParser.parse(it.exception).wrapToRequestResult()
            }
        }
    }
}