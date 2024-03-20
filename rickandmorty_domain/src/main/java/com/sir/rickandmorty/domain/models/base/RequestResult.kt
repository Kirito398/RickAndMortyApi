package com.sir.rickandmorty.domain.models.base

import com.sir.rickandmorty.domain.models.type.Failure

sealed class RequestResult<out R> {
    data class Success<out R>(val data: R) : RequestResult<R>()
    data class Error(val failure: Failure) : RequestResult<Nothing>()
}