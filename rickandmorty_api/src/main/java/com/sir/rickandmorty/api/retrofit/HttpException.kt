package com.sir.rickandmorty.api.retrofit

import java.lang.Exception

class HttpException(
    override val statusCode: Int,
    override val statusMessage: String?,
    cause: Throwable? = null,
) : Exception(null, cause), HttpResponse