package com.ipa.dev.gamethrones.data

data class CharacterModel (
    val id: Int,
    val fullName: String,
    val title: String,
    val family: String,
    val imageUrl: String
)

val listOfCharacters = (1..100).map {
    CharacterModel(
        id = it,
        fullName = "Full Name $it",
        title = "Title $it",
        family = "Family $it",
        imageUrl = "https://picsum.photos/200/300?id=$it"
    )
}