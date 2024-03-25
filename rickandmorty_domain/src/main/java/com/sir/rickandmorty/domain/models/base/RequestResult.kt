package com.sir.rickandmorty.domain.models.base

import com.sir.rickandmorty.domain.models.type.Failure

sealed class RequestResult<out R> {
    data class Success<out R>(val data: R) : RequestResult<R>()
    data class Error<out R>(val failure: Failure, val data: R? = null) : RequestResult<R>()
    data object InProgress : RequestResult<Nothing>()
}