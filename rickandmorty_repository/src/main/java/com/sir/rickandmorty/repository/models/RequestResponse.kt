package com.sir.rickandmorty.repository.models

import com.sir.rickandmorty.domain.models.type.Failure

sealed class RequestResponse<out R> {
    data class Success<out R>(val data: R) : RequestResponse<R>()
    data class Error(val failure: Failure) : RequestResponse<Nothing>()
}

fun <R> R.wrapToRequestResponse(): RequestResponse.Success<R> {
    return RequestResponse.Success(data = this)
}

fun Failure.wrapToRequestResponse(): RequestResponse.Error {
    return RequestResponse.Error(this)
}