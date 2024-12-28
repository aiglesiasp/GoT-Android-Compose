package com.ipa.dev.gamethrones.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterRemoteResult (
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val fullName: String?,
    val family: String?,
    val title: String?,
    val image: String?,
    val imageUrl: String?,
)
