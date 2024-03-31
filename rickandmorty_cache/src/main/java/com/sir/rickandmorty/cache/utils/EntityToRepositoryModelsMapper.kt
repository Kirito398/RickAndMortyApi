package com.sir.rickandmorty.cache.utils

import com.sir.rickandmorty.cache.room.entities.CharacterEntity
import com.sir.rickandmorty.domain.models.CharacterInfo

fun CharacterEntity.mapToRepositoryModel(): CharacterInfo {
    return CharacterInfo(
        id = id,
        name = name,
        status = status.mapToRepository(),
        species = species,
        type = type,
        gender = gender.mapToRepository(),
        origin = origin.mapToRepository(),
        location = location.mapToRepository(),
        image = image,
        episodeUrls = episodeUrls ?: emptyList(),
        characterUrl = characterUrl,
        createdDate = createdDate,
    )
}

fun CharacterInfo.mapToCacheModel(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status.mapToRepository(),
        species = species,
        type = type,
        gender = gender.mapToRepository(),
        origin = origin.mapToRepository(),
        location = location.mapToRepository(),
        image = image,
        episodeUrls = episodeUrls,
        characterUrl = characterUrl,
        createdDate = createdDate,
    )
}

private fun CharacterInfo.CharacterStatus.mapToRepository(): CharacterEntity.CharacterStatus {
    return when(this) {
        CharacterInfo.CharacterStatus.ALIVE -> CharacterEntity.CharacterStatus.ALIVE
        CharacterInfo.CharacterStatus.DEAD -> CharacterEntity.CharacterStatus.DEAD
        CharacterInfo.CharacterStatus.UNKNOWN -> CharacterEntity.CharacterStatus.UNKNOWN
    }
}

private fun CharacterInfo.CharacterGender.mapToRepository(): CharacterEntity.CharacterGender {
    return when(this) {
        CharacterInfo.CharacterGender.FEMALE -> CharacterEntity.CharacterGender.FEMALE
        CharacterInfo.CharacterGender.MALE -> CharacterEntity.CharacterGender.MALE
        CharacterInfo.CharacterGender.GENDERLESS -> CharacterEntity.CharacterGender.GENDERLESS
        CharacterInfo.CharacterGender.UNKNOWN -> CharacterEntity.CharacterGender.UNKNOWN
    }
}

private fun CharacterInfo.CharacterLocation.mapToRepository(): CharacterEntity.CharacterLocation {
    return CharacterEntity.CharacterLocation(
        name = name,
        url = url
    )
}

private fun CharacterInfo.CharacterOrigin.mapToRepository(): CharacterEntity.CharacterOrigin {
    return CharacterEntity.CharacterOrigin(
        name = name,
        url = url
    )
}

private fun CharacterEntity.CharacterStatus.mapToRepository(): CharacterInfo.CharacterStatus {
    return when(this) {
        CharacterEntity.CharacterStatus.ALIVE -> CharacterInfo.CharacterStatus.ALIVE
        CharacterEntity.CharacterStatus.DEAD -> CharacterInfo.CharacterStatus.DEAD
        CharacterEntity.CharacterStatus.UNKNOWN -> CharacterInfo.CharacterStatus.UNKNOWN
    }
}

private fun CharacterEntity.CharacterGender.mapToRepository(): CharacterInfo.CharacterGender {
    return when(this) {
        CharacterEntity.CharacterGender.FEMALE -> CharacterInfo.CharacterGender.FEMALE
        CharacterEntity.CharacterGender.MALE -> CharacterInfo.CharacterGender.MALE
        CharacterEntity.CharacterGender.GENDERLESS -> CharacterInfo.CharacterGender.GENDERLESS
        CharacterEntity.CharacterGender.UNKNOWN -> CharacterInfo.CharacterGender.UNKNOWN
    }
}

private fun CharacterEntity.CharacterLocation.mapToRepository(): CharacterInfo.CharacterLocation {
    return CharacterInfo.CharacterLocation(
        name = name,
        url = url
    )
}

private fun CharacterEntity.CharacterOrigin.mapToRepository(): CharacterInfo.CharacterOrigin {
    return CharacterInfo.CharacterOrigin(
        name = name,
        url = url
    )
}