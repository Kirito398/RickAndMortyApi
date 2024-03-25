package com.sir.rickandmorty.repository.utils

import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.domain.models.type.Failure
import com.sir.rickandmorty.repository.models.RequestResponse

class RequestResultMergeStrategy {
    fun <R> merge(left: RequestResponse<R>, right: RequestResponse<R>): RequestResult<R> {
        return when {
            left is RequestResponse.Success && right is RequestResponse.Success -> mergeStrategy(left, right)
            left is RequestResponse.Error && right is RequestResponse.Success -> mergeStrategy(left, right)
            left is RequestResponse.Success && right is RequestResponse.Error -> mergeStrategy(left, right)
            left is RequestResponse.Error && right is RequestResponse.Error -> mergeStrategy(left, right)
            else -> unsupportedCase()
        }
    }

    private fun <R> mergeStrategy(left: RequestResponse.Success<R>, right: RequestResponse.Success<R>): RequestResult<R> {
        return RequestResult.Success(left.data)
    }

    private fun <R> mergeStrategy(left: RequestResponse.Error, right: RequestResponse.Success<R>): RequestResult<R> {
        return RequestResult.Error(
            data = right.data,
            failure = left.failure
        )
    }

    private fun <R> mergeStrategy(left: RequestResponse.Success<R>, right: RequestResponse.Error): RequestResult<R> {
        return RequestResult.Error(
            data = left.data,
            failure = right.failure
        )
    }

    private fun <R> mergeStrategy(left: RequestResponse.Error, right: RequestResponse.Error): RequestResult<R> {
        return RequestResult.Error(left.failure)
    }

    private fun <R> unsupportedCase(): RequestResult<R> {
        return RequestResult.Error(Failure.UnknownError)
    }
}