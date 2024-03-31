package com.sir.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sir.entity.ui.theme.AppTheme
import com.sir.entity.ui.theme.RickAndMortyTheme
import com.sir.rickandmorty.features.characters.screens.CharactersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.primaryBackground
                ) {
                    CharactersScreen()
                }
            }
        }
    }
}
