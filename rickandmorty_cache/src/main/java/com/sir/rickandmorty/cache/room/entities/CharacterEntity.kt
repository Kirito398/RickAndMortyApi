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
    @Embedded("status.") val status: CharacterStatus,
    @ColumnInfo("species") val species: String,
    @ColumnInfo("type") val type: String,
    @Embedded("gender") val gender: CharacterGender,
    @Embedded("origin") val origin: CharacterOrigin,
    @Embedded("location") val location: CharacterLocation,
    @ColumnInfo("image") val image: String?,
    @ColumnInfo("episode") val episodeUrls: List<String>?,
    @ColumnInfo("url") val characterUrl: String?,
    @ColumnInfo("created") val createdDate: Date?
) {

    enum class CharacterStatus {
        @ColumnInfo("Alive")
        ALIVE,
        @ColumnInfo("Dead")
        DEAD,
        @ColumnInfo("unknown")
        UNKNOWN,
    }

    enum class CharacterGender {
        @ColumnInfo("Female")
        FEMALE,
        @ColumnInfo("Male")
        MALE,
        @ColumnInfo("Genderless")
        GENDERLESS,
        @ColumnInfo("unknown")
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