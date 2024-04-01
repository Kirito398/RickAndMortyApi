package com.sir.rickandmorty.api.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

class CharacterInfoResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("status") val status: CharacterStatus,
    @SerialName("species") val species: String,
    @SerialName("type") val type: String,
    @SerialName("gender") val gender: CharacterGender,
    @SerialName("origin") val origin: CharacterOrigin,
    @SerialName("location") val location: CharacterLocation,
    @SerialName("image") val image: String?,
    @SerialName("episode") val episodeUrls: List<String>?,
    @SerialName("url") val characterUrl: String?,
    @SerialName("created") val createdDate: Date?
) {

    @Serializable
    enum class CharacterStatus {
        @SerializedName("Alive") ALIVE,
        @SerializedName("Dead") DEAD,
        @SerializedName("unknown") UNKNOWN,
    }

    @Serializable
    enum class CharacterGender {
        @SerializedName("Female") FEMALE,
        @SerializedName("Male") MALE,
        @SerializedName("Genderless") GENDERLESS,
        @SerializedName("unknown") UNKNOWN,
    }

    data class CharacterOrigin(
        @SerialName("name") val name: String,
        @SerialName("url") val url: String?
    )

    data class CharacterLocation(
        @SerialName("name") val name: String,
        @SerialName("url") val url: String?
    )
}
