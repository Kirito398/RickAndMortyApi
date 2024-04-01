package com.sir.rickandmorty.features.characters.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sir.entity.ui.mvi.EventHandler
import com.sir.rickandmorty.domain.RickAndMortyInteractor
import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.features.characters.models.CharactersEvent
import com.sir.rickandmorty.features.characters.models.CharactersViewState
import com.sir.rickandmorty.features.characters.models.CharactersViewSubState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val interactor: RickAndMortyInteractor
) : ViewModel(), EventHandler<CharactersEvent> {

    private val _viewState = MutableStateFlow(CharactersViewState())
    val viewState: StateFlow<CharactersViewState> = _viewState.asStateFlow()

    override fun obtainEvent(event: CharactersEvent) {
        when (event) {
            CharactersEvent.UpdateCharactersList -> obtainUpdateCharactersListEvent()
        }
    }

    private fun obtainUpdateCharactersListEvent() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getCharactersList().collect { result ->
                when (result) {
                    is RequestResult.Error -> CharactersViewSubState.Error
                    is RequestResult.InProgress -> CharactersViewSubState.Loading
                    is RequestResult.Success -> CharactersViewSubState.Success
                }.let { subState ->
                    _viewState.update {
                        it.copy(
                            subState = subState,
                            charactersList = result.data?.results ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}
