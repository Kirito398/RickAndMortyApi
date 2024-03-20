package com.sir.rickandmorty.api.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallResponse<T>(
    private val call: Call<T>
) {
    fun runFlow(): Flow<ResponseResult<T>> {
        return flow { emit(run()) }
    }
    fun run(): ResponseResult<T> {
        return execute()
    }

    private fun execute(): ResponseResult<T> {
        return try {
            val response = call.execute()
            handleResponse(response)
        } catch (e: Throwable) {
            handleError(e)
        }
    }
    fun process(responseHandler: (ResponseResult<T>) -> Unit) {
        val callback = object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                responseHandler(handleResponse(response))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                handleError(t)
            }
        }

        call.enqueue(callback)
    }

    private fun handleResponse(response: Response<T>): ResponseResult<T> {
        return if (response.isSuccessful) {
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
    }

    private fun handleError(e: Throwable): ResponseResult.Failure {
        return when (e) {
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