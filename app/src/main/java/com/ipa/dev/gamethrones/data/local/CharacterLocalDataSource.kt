package com.ipa.dev.gamethrones.data.local

import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.local.database.CharacterDAO
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel

class CharacterLocalDataSource(
    private val dao: CharacterDAO
) {

    suspend fun getCharacters(): List<CharacterModel> = dao.getCharacters().map { it.toDomainModel() }

    suspend fun getCharacter(id: Int): CharacterModel? = dao.getCharacter(id)?.toDomainModel()

    suspend fun insertAll(characters: List<CharacterModel>) = dao.insertAll(characters.map { it.toLocalModel() })
}

private fun CharacterLocalModel.toDomainModel(): CharacterModel {
    return CharacterModel(
        id = id,
        fullName = if (fullName.isNullOrBlank()) "" else fullName,
        title = if (title.isNullOrBlank()) "" else title,
        family = if (family.isNullOrBlank()) "" else family,
        imageUrl = if (imageUrl.isNullOrBlank()) "" else imageUrl
    )
}

private fun CharacterModel.toLocalModel(): CharacterLocalModel {
    return CharacterLocalModel(
        id = id,
        firstName = "",
        lastName = "",
        fullName = if (fullName.isNullOrBlank()) "" else fullName,
        title = if (title.isNullOrBlank()) "" else title,
        family = if (family.isNullOrBlank()) "" else family,
        image = "",
        imageUrl = if (imageUrl.isNullOrBlank()) "" else imageUrl
    )
}