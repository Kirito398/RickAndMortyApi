package com.sir.rickandmorty.features.characters.models

import com.sir.rickandmorty.domain.models.CharactersFilter as CharactersFilterDomain

data class CharactersFilter(
    val name: String = ""
)

fun CharactersFilter.mapToDomain() = CharactersFilterDomain(
    name = this.name
)