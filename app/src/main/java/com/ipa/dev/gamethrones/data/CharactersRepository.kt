package com.ipa.dev.gamethrones.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CharactersRepository {

    suspend fun getCharacters(): List<CharacterModel> = withContext(Dispatchers.IO) {
        delay(2000)
        listOfCharacters
    }
}