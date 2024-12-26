package com.ipa.dev.gamethrones.data

class CharactersRepository {

    suspend fun getCharacters(): List<CharacterModel> {
        val remoteCharacters = ApiClient.instance.getCharacters()
        return remoteCharacters.map { it.toDomainModel() }
    }

    suspend fun getCharacter(id: Int): CharacterModel {
        val remoteCharacter = ApiClient.instance.getCharacter(id)
        return remoteCharacter.toDomainModel()
    }
}

private fun CharacterRemoteResult.toDomainModel(): CharacterModel {
    return CharacterModel(
        id = id,
        fullName = if (fullName.isNullOrBlank()) "" else fullName,
        title = if (title.isNullOrBlank()) "" else title,
        family = if (family.isNullOrBlank()) "" else family,
        imageUrl = if (imageUrl.isNullOrBlank()) "" else imageUrl
    )
}
