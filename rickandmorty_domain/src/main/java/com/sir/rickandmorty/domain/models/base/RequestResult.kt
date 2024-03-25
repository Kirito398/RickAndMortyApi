package com.sir.rickandmorty.domain.models.base

import com.sir.rickandmorty.domain.models.type.Failure

sealed class RequestResult<out R> {
    data class Success<out R>(val data: R) : RequestResult<R>()
    data class Error(val failure: Failure) : RequestResult<Nothing>()
}

fun <R> R.wrapToRequestResult(): RequestResult.Success<R> {
    return RequestResult.Success(data = this)
}

fun Failure.wrapToRequestResult(): RequestResult.Error {
    return RequestResult.Error(this)
}