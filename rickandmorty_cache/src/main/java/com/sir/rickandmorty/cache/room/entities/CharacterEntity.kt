package com.sir.rickandmorty.cache.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "characters")
class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("status") val status: CharacterStatus,
    @ColumnInfo("species") val species: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("gender") val gender: CharacterGender,
    @Embedded("origin.") val origin: CharacterOrigin,
    @Embedded("location.") val location: CharacterLocation,
    @ColumnInfo("image") val image: String?,
    @ColumnInfo("episode") val episodeUrls: List<String>?,
    @ColumnInfo("url") val characterUrl: String?,
    @ColumnInfo("created") val createdDate: Date?
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
        @ColumnInfo("name") val name: String,
        @ColumnInfo("url") val url: String?
    )

    data class CharacterLocation(
        @ColumnInfo("name") val name: String,
        @ColumnInfo("url") val url: String?
    )
}