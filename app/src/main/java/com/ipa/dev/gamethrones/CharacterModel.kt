package com.ipa.dev.gamethrones

data class CharacterModel (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val image: String,
    val imageUrl: String
)

val listOfCharacters = (1..100).map {
    CharacterModel(
        id = it,
        firstName = "First Name $it",
        lastName = "Last Name $it",
        fullName = "Full Name $it",
        title = "Title $it",
        family = "Family $it",
        image = "Image $it",
        imageUrl = "https://picsum.photos/200/300?id=$it"
    )
}