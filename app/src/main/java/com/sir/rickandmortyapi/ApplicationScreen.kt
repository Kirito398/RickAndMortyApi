package com.sir.rickandmortyapi

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sir.rickandmorty.features.characters.screens.CharactersScreen
import com.sir.rickandmortyapi.navigation.NavigationTree

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.CharactersList.name) {
        composable(NavigationTree.CharactersList.name) { CharactersScreen() }
    }
}
