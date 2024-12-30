package com.ipa.dev.gamethrones.data.local

import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.local.database.CharacterDAO
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel
import kotlinx.coroutines.flow.Flow

class CharacterLocalDataSource(
    private val dao: CharacterDAO
) {

    val characters: Flow<List<CharacterLocalModel>> = dao.getCharacters()

    fun getCharacter(id: Int): Flow<CharacterLocalModel?> = dao.getCharacter(id)

    suspend fun insertAll(characters: List<CharacterLocalModel>) = dao.insertAll(characters)
}

