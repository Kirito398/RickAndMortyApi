package com.sir.rickandmorty.api.utils

import com.sir.entity.api.retrofit.HttpResponse
import com.sir.rickandmorty.domain.models.type.Failure

class ErrorParser {
    fun parse(response: HttpResponse): Failure {
        return when (response.statusCode) {
            404 -> Failure.ServerNotFoundError
            else -> Failure.UnknownError
        }
    }
}