package com.sir.rickandmorty.features.characters.viewmodels

import androidx.lifecycle.viewModelScope
import com.sir.entity.ui.mvi.BaseViewModel
import com.sir.rickandmorty.domain.RickAndMortyInteractor
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo.*
import com.sir.rickandmorty.domain.models.base.RequestResult
import com.sir.rickandmorty.features.characters.models.CharactersEvent
import com.sir.rickandmorty.features.characters.models.CharactersFilter
import com.sir.rickandmorty.features.characters.models.CharactersViewState
import com.sir.rickandmorty.features.characters.models.CharactersViewSubState
import com.sir.rickandmorty.features.characters.models.mapToDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val interactor: RickAndMortyInteractor
) : BaseViewModel<CharactersEvent, CharactersViewState>(CharactersViewState()) {

    override fun obtainEvent(event: CharactersEvent) {
        when (event) {
            CharactersEvent.UpdateCharactersList -> obtainUpdateCharactersListEvent()
            CharactersEvent.LoadCharacterNextPage -> obtainLoadCharacterNextPageEvent()
            is CharactersEvent.UpdateCharacterFilterName -> obtainUpdateCharacterFilterName(event.name)
        }
    }

    private fun obtainUpdateCharacterFilterName(name: String) {
        mutableViewState.update {
            it.copy(
                filter = CharactersFilter(name = name)
            )
        }
    }

    private fun obtainLoadCharacterNextPageEvent() {
        loadCharacterList()
    }

    private fun obtainUpdateCharactersListEvent() {
        loadCharacterList(isForce = true)
    }

    private fun loadCharacterList(isForce: Boolean = false) {
        if (viewState.value.hasNextPage.not()
            && viewState.value.subState == CharactersViewSubState.Loading) return

        val loadingPage = if (isForce) 1 else viewState.value.currentPage + 1
        val filter = viewState.value.filter

        viewModelScope.launch(Dispatchers.IO) {
            interactor.getCharactersList(
                page = loadingPage,
                filter = filter.mapToDomain()
            ).collect { result ->
                when (result) {
                    is RequestResult.Error -> CharactersViewSubState.Error
                    is RequestResult.InProgress -> CharactersViewSubState.Loading
                    is RequestResult.Success -> CharactersViewSubState.Success
                }.let { subState ->
                    updateCharacterListState(
                        subState = subState,
                        data = result.data,
                        page = loadingPage,
                        isForce = isForce
                    )
                }
            }
        }
    }

    private fun updateCharacterListState(
        subState: CharactersViewSubState,
        data: CharactersWithPaginationInfo?,
        page: Int,
        isForce: Boolean
    ) {
        val resultList = data?.results ?: emptyList()

        val newList = if (isForce) {
            resultList
        } else {
            viewState.value.charactersList.toMutableList().apply {
                addAll(
                    resultList.filter { item ->
                        find { it.id == item.id } == null
                    }
                )
            }
        }

        mutableViewState.update {
            it.copy(
                subState = subState,
                charactersList = newList,
                pageCount = data?.info?.pages ?: PaginationInfo.DEFAULT_PAGES_COUNT,
                currentPage = page
            )
        }
    }
}
