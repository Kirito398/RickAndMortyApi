package com.sir.rickandmorty.domain.models.base

import com.sir.rickandmorty.domain.models.type.Failure

sealed class RequestResult<out R>(open val data: R?) {
    data class Success<out R>(override val data: R) : RequestResult<R>(data)
    data class Error<out R>(val failure: Failure, override val data: R? = null) : RequestResult<R>(data)
    data class InProgress<out R>(override val data: R? = null) : RequestResult<R>(data)
}
