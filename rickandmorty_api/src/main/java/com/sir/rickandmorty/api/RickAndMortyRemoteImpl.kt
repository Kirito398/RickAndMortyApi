package com.sir.rickandmorty.api

import com.sir.entity.api.retrofit.ResponseResult
import com.sir.rickandmorty.api.models.CharactersResponse
import com.sir.rickandmorty.api.utils.ErrorParser
import com.sir.rickandmorty.api.utils.mapToDomainFormat
import com.sir.rickandmorty.domain.models.CharactersFilter
import com.sir.rickandmorty.repository.interfaces.RickAndMortyRemote
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.type.Failure
import com.sir.rickandmorty.domain.utils.ifEmptyNull
import com.sir.rickandmorty.repository.models.RequestResponse
import com.sir.rickandmorty.repository.models.wrapToRequestResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickAndMortyRemoteImpl(
    private val api: RickAndMortyApi,
    private val errorParser: ErrorParser,
) : RickAndMortyRemote {
    override fun getCharacters(
        page: Int?,
        filter: CharactersFilter,
    ): Flow<RequestResponse<CharactersWithPaginationInfo>> {
        return api.getAll(
            page = page,
            name = filter.name.ifEmptyNull(),
        ).runFlow()
            .mapToRequestResponse(CharactersResponse::mapToDomainFormat)
    }

    private fun <R, T> Flow<ResponseResult<R>>.mapToRequestResponse(
        mapToDomainFormat: R.() -> T
    ): Flow<RequestResponse<T>> {
        return this.map {
            when (it) {
                is ResponseResult.Success.Empty -> errorParser.parse(it).wrapToRequestResponse()
                is ResponseResult.Success.HttpResponse -> it.value.mapToDomainFormat().wrapToRequestResponse()
                is ResponseResult.Failure.Error -> Failure.ThrowableError(throwable = it.error).wrapToRequestResponse()
                is ResponseResult.Failure.HttpError -> errorParser.parse(it.exception).wrapToRequestResponse()
            }
        }
    }
}