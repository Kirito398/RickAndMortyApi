package com.sir.rickandmorty.features.characters.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sir.entity.ui.theme.AppTheme
import com.sir.entity.ui.view.PaginationList
import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.features.characters.R
import com.sir.rickandmorty.features.characters.models.CharactersEvent
import com.sir.rickandmorty.features.characters.models.CharactersViewSubState
import com.sir.rickandmorty.features.characters.viewmodels.CharactersViewModel
import com.sir.rickandmorty.features.characters.views.ProgressIndicator

@Composable
fun CharactersScreen() {
    CharactersScreen(viewModel = hiltViewModel())
}

@Composable
internal fun CharactersScreen(
    viewModel: CharactersViewModel
) {
    val state by viewModel.viewState.collectAsState()

    if (state.subState == CharactersViewSubState.None) {
        viewModel.obtainEvent(CharactersEvent.UpdateCharactersList)
    }

    Column {
        if (state.subState == CharactersViewSubState.Loading) {
            ProgressIndicator()
        }

        if (state.subState == CharactersViewSubState.Error) {
            ErrorMessage()
        }

        if (state.charactersList.isNotEmpty()) {
            Characters(state.charactersList) {
                viewModel.obtainEvent(CharactersEvent.LoadCharacterNextPage)
            }
        }
    }
}

@Composable
internal fun Characters(
    charactersList: List<CharacterInfo>,
    loadNewPage: () -> Unit
) {
    PaginationList(
        itemList = charactersList,
        loadNewPage = loadNewPage
    ) { item ->
        CharacterItem(item)
    }
}

@Composable
internal fun CharacterItem(info: CharacterInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(AppTheme.dimensions.defaultPadding)
            .clip(RoundedCornerShape(AppTheme.dimensions.defaultPadding))
            .background(AppTheme.colors.primarySecondBackground)
    ) {
        info.image?.let { imageUrl ->
            CharacterAvatar(imageUrl)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.defaultPadding)
        ) {

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = info.name, color = AppTheme.colors.headerTextColor, style = AppTheme.typography.titleMedium)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CharacterStatusMarker(status = info.status)
                    Text(text = "${info.status.name} - ${info.species}", color = AppTheme.colors.primaryTextColor)
                }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.title_last_known_location), color = AppTheme.colors.primarySecondTextColor)
                Text(text = info.location.name, color = AppTheme.colors.primaryTextColor)
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.title_first_seen_in), color = AppTheme.colors.primarySecondTextColor)
                Text(text = "No implemented yet!", color = AppTheme.colors.primaryTextColor)
            }
        }
    }
}

@Composable
private fun CharacterStatusMarker(status: CharacterInfo.CharacterStatus) {
    val markColor = when(status) {
        CharacterInfo.CharacterStatus.ALIVE -> AppTheme.colors.statusAlive
        CharacterInfo.CharacterStatus.DEAD -> AppTheme.colors.statusDead
        CharacterInfo.CharacterStatus.UNKNOWN -> AppTheme.colors.statusUnknown
    }
    Box(
        modifier = Modifier
            .padding(end = AppTheme.dimensions.defaultPadding)
            .size(size = AppTheme.dimensions.markerSize)
            .clip(shape = CircleShape)
            .background(color = markColor)
    )
}

@Composable
internal fun CharacterAvatar(imageUrl: String) {
    var isImageVisible by remember { mutableStateOf(true) }
    if (isImageVisible) {
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.cont_desc_character_avatar),
            onError = {
                isImageVisible = false
            },
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@Composable
internal fun ErrorMessage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.error)
            .padding(AppTheme.dimensions.defaultPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.update_error),
            color = AppTheme.colors.primaryTextColor
        )
    }
}
