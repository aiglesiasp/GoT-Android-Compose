package com.ipa.dev.gamethrones.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class CharacterLocalModel (
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val firstName: String?,
    val lastName: String?,
    val fullName: String?,
    val family: String?,
    val title: String?,
    val image: String?,
    val imageUrl: String?,
    val isFavorite: Boolean
)