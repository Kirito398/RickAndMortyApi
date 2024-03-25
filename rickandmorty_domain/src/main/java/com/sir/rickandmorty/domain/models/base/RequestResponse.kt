package com.sir.rickandmorty.domain.models.base

import com.sir.rickandmorty.domain.models.type.Failure

sealed class RequestResponse<out R> {
    data class Success<out R>(val data: R) : RequestResponse<R>()
    data class Error(val failure: Failure) : RequestResponse<Nothing>()
}

fun <R> R.wrapToRequestResult(): RequestResponse.Success<R> {
    return RequestResponse.Success(data = this)
}

fun Failure.wrapToRequestResult(): RequestResponse.Error {
    return RequestResponse.Error(this)
}