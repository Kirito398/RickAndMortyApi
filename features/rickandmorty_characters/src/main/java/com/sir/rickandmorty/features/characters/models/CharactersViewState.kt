package com.sir.rickandmorty.features.characters.models

import com.sir.rickandmorty.domain.models.CharacterInfo

enum class CharactersViewSubState {
    None, Success, Error, Loading
}

data class CharactersViewState(
    val subState: CharactersViewSubState = CharactersViewSubState.None,
    val charactersList: List<CharacterInfo> = emptyList(),
)
