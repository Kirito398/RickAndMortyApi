package com.sir.rickandmorty.domain.models

import java.util.Date

class CharacterInfo(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: CharacterOrigin,
    val location: CharacterLocation,
    val image: String?,
    val episodeUrls: List<String>,
    val characterUrl: String,
    val createdDate: Date?
) {

    enum class CharacterStatus {
        ALIVE,
        DEAD,
        UNKNOWN,
    }

    enum class CharacterGender {
        FEMALE,
        MALE,
        GENDERLESS,
        UNKNOWN,
    }

    data class CharacterOrigin(
        val name: String,
        val url: String?
    )

    data class CharacterLocation(
        val name: String,
        val url: String?
    )
}