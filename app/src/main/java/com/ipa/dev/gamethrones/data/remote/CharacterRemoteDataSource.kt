package com.ipa.dev.gamethrones.data.remote

import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.remote.api.ApiClient
import com.ipa.dev.gamethrones.data.remote.model.CharacterRemoteResult

class CharacterRemoteDataSource {
    suspend fun getCharacters(): List<CharacterModel> {
        val remoteCharacters = ApiClient.instance.getCharacters()
        return remoteCharacters.map { it.toDomainModel() } }

    suspend fun getCharacter(id: Int): CharacterModel {
        val remoteCharacters =  ApiClient.instance.getCharacter(id)
        return remoteCharacters.toDomainModel()
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