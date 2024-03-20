package com.sir.rickandmorty.api.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

class CallResponse<T>(
    private val call: Call<T>
) {
    fun run(): ResponseResult<T> {
        return execute()
    }

    fun runFlow(): Flow<ResponseResult<T>> {
        return flow { emit(run()) }
    }

    private fun execute(): ResponseResult<T> {
        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseResult.Success.HttpResponse(
                        value = it,
                        statusCode = response.code(),
                        statusMessage = response.message()
                    )
                } ?: ResponseResult.Success.Empty(
                    statusCode = response.code(),
                    statusMessage = response.message()
                )
            } else {
                ResponseResult.Failure.HttpError(
                    HttpException(
                        statusCode = response.code(),
                        statusMessage = response.message()
                    )
                )
            }
        } catch (e: Throwable) {
            when (e) {
                is retrofit2.HttpException -> ResponseResult.Failure.HttpError(
                    HttpException(
                        statusCode = e.code(),
                        statusMessage = e.message()
                    )
                )
                else -> ResponseResult.Failure.Error(error = e)
            }
        }
    }
}