package com.ipa.dev.gamethrones.data

import com.ipa.dev.gamethrones.data.remotedatasource.CharacterRemoteDataSource

class CharactersRepository(
    private val remoteDataSource: CharacterRemoteDataSource
) {

    suspend fun getCharacters(): List<CharacterModel> = remoteDataSource.getCharacters()

    suspend fun getCharacter(id: Int): CharacterModel = remoteDataSource.getCharacter(id)
}


