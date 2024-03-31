package com.sir.rickandmorty.features.characters.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sir.rickandmorty.domain.RickAndMortyInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: RickAndMortyInteractor
) : ViewModel() {

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
