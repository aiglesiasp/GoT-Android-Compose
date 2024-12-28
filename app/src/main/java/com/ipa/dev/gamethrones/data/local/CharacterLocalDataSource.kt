package com.ipa.dev.gamethrones.data.local

import com.ipa.dev.gamethrones.data.local.database.CharacterDAO
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel

class CharacterLocalDataSource(
    private val dao: CharacterDAO
) {

    suspend fun getCharacters(): List<CharacterLocalModel> = dao.getCharacters()

    suspend fun getCharacter(id: Int): CharacterLocalModel = dao.getCharacter(id)

    suspend fun insertAll(characters: List<CharacterLocalModel>) = dao.insertAll(characters)
}