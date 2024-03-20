package com.sir.rickandmorty.domain.models

data class CharactersWithPaginationInfo(
    val info: PaginationInfo,
    val results: List<CharacterInfo>
) {

    data class PaginationInfo(
        val count: Int,
        val pages: Int,
        //val next: String?,
        //val prev: String?
    )
}