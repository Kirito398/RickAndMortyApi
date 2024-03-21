package com.sir.entity.api.retrofit

sealed class ResponseResult<out R> {
    sealed class Success<R> : ResponseResult<R>(), HttpResponse {
        data class Empty(
            override val statusCode: Int,
            override val statusMessage: String?,
        ) : Success<Nothing>()

        data class HttpResponse<R>(
            val value: R,
            override val statusCode: Int,
            override val statusMessage: String?,
        ) : Success<R>()
    }
    sealed class Failure : ResponseResult<Nothing>() {
        data class Error(val error: Throwable) : Failure()
        data class HttpError(val exception: HttpException) : Failure()
    }
}