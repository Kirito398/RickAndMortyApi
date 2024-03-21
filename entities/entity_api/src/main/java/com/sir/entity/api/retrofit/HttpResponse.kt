package com.sir.entity.api.retrofit

interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
}