package com.ipa.dev.gamethrones.data

import com.ipa.dev.gamethrones.data.local.CharacterLocalDataSource
import com.ipa.dev.gamethrones.data.remote.CharacterRemoteDataSource

class CharactersRepository(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource
) {

    suspend fun getCharacters(): List<CharacterModel> {
        if (localDataSource.getCharacters().isEmpty()) {
            val characters = remoteDataSource.getCharacters()
            localDataSource.insertAll(characters)
        }
        return localDataSource.getCharacters()
    }

    suspend fun getCharacter(id: Int): CharacterModel {
        if (localDataSource.getCharacter(id) == null) {
            val character = remoteDataSource.getCharacter(id)
            localDataSource.insertAll(listOf(character))
        }
        return checkNotNull( localDataSource.getCharacter(id) )
    }
}


