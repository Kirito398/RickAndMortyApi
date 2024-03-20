package com.sir.rickandmorty.api.models

import kotlinx.serialization.SerialName

data class CharactersResponse(
    @SerialName("info") val info: PaginationInfo,
    @SerialName("results") val results: List<CharacterInfoResponse>
) {

    data class PaginationInfo(
        @SerialName("count") val count: Int,
        @SerialName("pages") val pages: Int,
        @SerialName("next") val next: String?,
        @SerialName("prev") val prev: String?
    )
}