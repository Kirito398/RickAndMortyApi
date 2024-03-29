package com.sir.rickandmorty.repository.utils

import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.domain.models.type.Failure
import com.sir.rickandmorty.repository.models.RequestResponse

class RequestResultMergeStrategy {
    fun <R> merge(left: RequestResult<R>, right: RequestResult<R>): RequestResult<R> {
        return when {
            left is RequestResult.Success && right is RequestResult.Success -> mergeStrategy(left, right)
            left is RequestResult.Success && right is RequestResult.Error -> mergeStrategy(left, right)
            left is RequestResult.Success && right is RequestResult.InProgress -> mergeStrategy(left, right)
            left is RequestResult.Error && right is RequestResult.Success -> mergeStrategy(left, right)
            left is RequestResult.Error && right is RequestResult.Error -> mergeStrategy(left, right)
            left is RequestResult.Error && right is RequestResult.InProgress -> mergeStrategy(left, right)
            left is RequestResult.InProgress && right is RequestResult.Success -> mergeStrategy(left, right)
            left is RequestResult.InProgress && right is RequestResult.Error -> mergeStrategy(left, right)
            left is RequestResult.InProgress && right is RequestResult.InProgress -> mergeStrategy(left, right)
            else -> unsupportedCase()
        }
    }

    private fun <R> mergeStrategy(left: RequestResult.Success<R>, right: RequestResult.Success<R>): RequestResult<R> {
        return RequestResult.Success(left.data)
    }

    private fun <R> mergeStrategy(left: RequestResult.Success<R>, right: RequestResult.Error<R>): RequestResult<R> {
        return RequestResult.Error(
            data = left.data,
            failure = right.failure
        )
    }

    private fun <R> mergeStrategy(left: RequestResult.Success<R>, right: RequestResult.InProgress<R>): RequestResult<R> {
        return RequestResult.Success(
            data = left.data
        )
    }

    private fun <R> mergeStrategy(left: RequestResult.Error<R>, right: RequestResult.Success<R>): RequestResult<R> {
        return RequestResult.Error(
            data = right.data,
            failure = left.failure
        )
    }

    private fun <R> mergeStrategy(left: RequestResult.Error<R>, right: RequestResult.Error<R>): RequestResult<R> {
        return RequestResult.Error(left.failure)
    }

    private fun <R> mergeStrategy(left: RequestResult.Error<R>, right: RequestResult.InProgress<R>): RequestResult<R> {
        return RequestResult.Error(
            data = right.data,
            failure = left.failure
        )
    }

    private fun <R> mergeStrategy(left: RequestResult.InProgress<R>, right: RequestResult.Success<R>): RequestResult<R> {
        return RequestResult.InProgress(right.data)
    }

    private fun <R> mergeStrategy(left: RequestResult.InProgress<R>, right: RequestResult.Error<R>): RequestResult<R> {
        return RequestResult.InProgress(right.data ?: left.data)
    }

    private fun <R> mergeStrategy(left: RequestResult.InProgress<R>, right: RequestResult.InProgress<R>): RequestResult<R> {
        return RequestResult.InProgress(left.data ?: right.data)
    }

    private fun <R> unsupportedCase(): RequestResult<R> {
        return RequestResult.Error(Failure.UnknownError)
    }
}