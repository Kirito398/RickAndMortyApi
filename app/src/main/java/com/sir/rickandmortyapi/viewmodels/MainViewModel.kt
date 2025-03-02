package com.sir.rickandmortyapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sir.rickandmorty.api.RickAndMortyApi
import com.sir.rickandmorty.api.RickAndMortyRemoteImpl
import com.sir.rickandmorty.api.utils.ErrorParser
import com.sir.rickandmorty.database.RickAndMortyRepositoryImpl
import com.sir.rickandmorty.domain.RickAndMortyInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val interactor = RickAndMortyInteractor(
        rickAndMortyRepository = RickAndMortyRepositoryImpl(
            remote = RickAndMortyRemoteImpl(
                api = RickAndMortyApi(
                    baseUrl = "https://rickandmortyapi.com/api/"
                ),
                errorParser = ErrorParser()
            )
        )
    )

    fun getCharacterList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactor.getCharactersList().collect {
                    it.toString()
                }
            }
        }
    }
}