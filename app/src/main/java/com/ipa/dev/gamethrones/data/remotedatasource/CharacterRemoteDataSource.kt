package com.ipa.dev.gamethrones.data.remotedatasource

import com.ipa.dev.gamethrones.data.ApiClient
import com.ipa.dev.gamethrones.data.CharacterRemoteResult

class CharacterRemoteDataSource {
    suspend fun getCharacters(): List<CharacterRemoteResult> {
        return ApiClient.instance.getCharacters()
    }

    suspend fun getCharacter(id: Int): CharacterRemoteResult {
        return ApiClient.instance.getCharacter(id)
    }
}