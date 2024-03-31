package com.sir.rickandmorty.features.characters.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sir.rickandmorty.features.characters.viewmodels.CharactersViewModel

@Composable
fun CharactersScreen() {
    CharactersScreen(viewModel = viewModel())
}

@Composable
internal fun CharactersScreen(
    viewModel: CharactersViewModel
) {

}
