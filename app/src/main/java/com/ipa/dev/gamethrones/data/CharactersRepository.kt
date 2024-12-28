package com.ipa.dev.gamethrones.data

import com.ipa.dev.gamethrones.data.remotedatasource.CharacterRemoteDataSource

class CharactersRepository(
    private val remoteDataSource: CharacterRemoteDataSource
) {

    suspend fun getCharacters(): List<CharacterModel> {
        val remoteCharacters = remoteDataSource.getCharacters()
        return remoteCharacters.map { it.toDomainModel() }
    }

    suspend fun getCharacter(id: Int): CharacterModel {
        val remoteCharacter = remoteDataSource.getCharacter(id)
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
