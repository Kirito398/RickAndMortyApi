package com.sir.rickandmorty.domain.models.type

sealed class Failure {
    data object NetworkConnectionError : Failure()
    data object ServerError : Failure()
    data object ServerNotFoundError : Failure()
    data object UnknownError : Failure()
    data class ThrowableError(val throwable: Throwable) : Failure()
}