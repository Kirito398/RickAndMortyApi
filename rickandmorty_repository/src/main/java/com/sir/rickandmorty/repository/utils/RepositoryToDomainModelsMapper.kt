package com.sir.rickandmorty.repository.utils

import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.repository.models.RequestResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <R> Flow<RequestResponse<R>>.mapToDomain(): Flow<RequestResult<R>> {
    return transform {
        when (it) {
            is RequestResponse.Error -> RequestResult.Error(it.failure)
            is RequestResponse.Success -> RequestResult.Success(it.data)
        }
    }
}