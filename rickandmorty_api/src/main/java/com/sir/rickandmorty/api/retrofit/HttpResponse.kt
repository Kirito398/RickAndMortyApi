package com.sir.rickandmorty.api.retrofit

interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
}