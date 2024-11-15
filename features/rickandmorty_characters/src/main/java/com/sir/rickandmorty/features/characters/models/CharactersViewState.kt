package com.sir.rickandmorty.features.characters.models

import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo.*

enum class CharactersViewSubState {
    None, Success, Error, Loading
}

data class CharactersViewState(
    val subState: CharactersViewSubState = CharactersViewSubState.None,
    val charactersList: List<CharacterInfo> = emptyList(),
    val currentPage: Int = 0,
    val pageCount: Int = PaginationInfo.DEFAULT_PAGES_COUNT,
    val hasNextPage: Boolean = currentPage < pageCount,
    val filter: CharactersFilter = CharactersFilter(),
)
