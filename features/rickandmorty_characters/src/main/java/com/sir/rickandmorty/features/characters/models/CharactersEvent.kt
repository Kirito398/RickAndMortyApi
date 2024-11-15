package com.sir.rickandmorty.features.characters.models

sealed class CharactersEvent {
    data object UpdateCharactersList : CharactersEvent()
    data object LoadCharacterNextPage : CharactersEvent()
    data class UpdateCharacterFilterName(val name: String) : CharactersEvent()
}
