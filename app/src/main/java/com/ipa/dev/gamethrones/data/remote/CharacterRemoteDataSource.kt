package com.ipa.dev.gamethrones.data.remote

import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.remote.api.ApiClient
import com.ipa.dev.gamethrones.data.remote.model.CharacterRemoteResult

class CharacterRemoteDataSource {
    suspend fun getCharacters(): List<CharacterRemoteResult> = ApiClient.instance.getCharacters()

    suspend fun getCharacter(id: Int): CharacterRemoteResult = ApiClient.instance.getCharacter(id)
}

