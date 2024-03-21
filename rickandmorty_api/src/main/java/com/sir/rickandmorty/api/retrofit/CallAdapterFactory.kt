package com.sir.rickandmorty.api.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)
        if (rawReturnType == CallResponse::class.java) {
            if (returnType is ParameterizedType) {
                val callInnerType = getParameterUpperBound(0, returnType)
                return CallAdapter<Any?>(callInnerType)
            }
            return CallAdapter<Nothing>(Nothing::class.java)
        }

        return null
    }
}

private class CallAdapter<R>(
    private val type: Type,
) : CallAdapter<R, CallResponse<R>> {
    override fun responseType(): Type {
        return type
    }

    override fun adapt(call: Call<R>): CallResponse<R> {
        return CallResponse(call)
    }
}