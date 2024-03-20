package com.sir.rickandmorty.api.utils

import com.sir.rickandmorty.api.models.CharacterInfoResponse
import com.sir.rickandmorty.api.models.CharactersResponse
import com.sir.rickandmorty.domain.models.CharacterInfo
import com.sir.rickandmorty.domain.models.CharactersWithPaginationInfo

fun CharactersResponse.mapToDomainFormat(): CharactersWithPaginationInfo {
    return CharactersWithPaginationInfo(
        info = this.info.mapToDomainFormat(),
        results = this.results.map { it.mapToDomainFormat() },
    )
}

private fun CharactersResponse.PaginationInfo.mapToDomainFormat(): CharactersWithPaginationInfo.PaginationInfo {
    return CharactersWithPaginationInfo.PaginationInfo(
        count = this.count,
        pages = this.pages
    )
}

private fun CharacterInfoResponse.mapToDomainFormat(): CharacterInfo {
    return CharacterInfo(
        id = this.id,
        name = this.name,
        status = this.status.mapToDomainFormat(),
        species = this.species,
        type = this.type,
        gender = this.gender.mapToDomainFormat(),
        origin = this.origin.mapToDomainFormat(),
        location = this.location.mapToDomainFormat(),
        image = this.image,
        episodeUrls = this.episodeUrls ?: emptyList(),
        characterUrl = this.characterUrl ?: "",
        createdDate = this.createdDate,
    )
}

private fun CharacterInfoResponse.CharacterLocation.mapToDomainFormat(): CharacterInfo.CharacterLocation {
    return CharacterInfo.CharacterLocation(
        name = this.name,
        url = this.url
    )
}

private fun CharacterInfoResponse.CharacterOrigin.mapToDomainFormat(): CharacterInfo.CharacterOrigin {
    return CharacterInfo.CharacterOrigin(
        name = this.name,
        url = this.url
    )
}

private fun CharacterInfoResponse.CharacterGender?.mapToDomainFormat(): CharacterInfo.CharacterGender {
    return when (this) {
        CharacterInfoResponse.CharacterGender.FEMALE -> CharacterInfo.CharacterGender.FEMALE
        CharacterInfoResponse.CharacterGender.MALE -> CharacterInfo.CharacterGender.MALE
        CharacterInfoResponse.CharacterGender.GENDERLESS -> CharacterInfo.CharacterGender.GENDERLESS
        CharacterInfoResponse.CharacterGender.UNKNOWN -> CharacterInfo.CharacterGender.UNKNOWN
        null -> CharacterInfo.CharacterGender.UNKNOWN
    }
}

private fun CharacterInfoResponse.CharacterStatus?.mapToDomainFormat(): CharacterInfo.CharacterStatus {
    return when (this) {
        CharacterInfoResponse.CharacterStatus.ALIVE -> CharacterInfo.CharacterStatus.ALIVE
        CharacterInfoResponse.CharacterStatus.DEAD -> CharacterInfo.CharacterStatus.DEAD
        CharacterInfoResponse.CharacterStatus.UNKNOWN -> CharacterInfo.CharacterStatus.UNKNOWN
        null -> CharacterInfo.CharacterStatus.UNKNOWN
    }
}