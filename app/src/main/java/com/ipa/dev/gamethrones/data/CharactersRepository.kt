package com.ipa.dev.gamethrones.data

import com.ipa.dev.gamethrones.data.local.CharacterLocalDataSource
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel
import com.ipa.dev.gamethrones.data.remote.CharacterRemoteDataSource
import com.ipa.dev.gamethrones.data.remote.model.CharacterRemoteResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class CharactersRepository(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource
) {

    val characters: Flow<List<CharacterModel>> = localDataSource.characters.transform { characterLocalModels ->
        //Cogemos los characterLocalModels si no estan vacios, si esta vacio peticion a red y guardamos en base de datos
        val charactersLocal = characterLocalModels.takeIf { it.isNotEmpty() } ?: remoteDataSource.getCharacters().toLocalModel().also { localDataSource.insertAll(it) }
        //Emitimos siempre valores de local
        emit(charactersLocal.toDomainModel())
    }

    fun getCharacter(id: Int): Flow<CharacterModel?> = localDataSource.getCharacter(id).transform { characterLocalModel ->
        //Comprobar si esta vacia para hacer peticion a RED si es necesario y guardar en base de datos
        val characterLocal = characterLocalModel.takeIf { it != null } ?: remoteDataSource.getCharacter(id).toLocalModel().also { localDataSource.insertAll(listOf(it)) }

        //Emitimos siempre valores de local
        emit(characterLocalModel?.toDomainModel())
    }
}

private fun CharacterRemoteResult.toLocalModel(): CharacterLocalModel {
    return CharacterLocalModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        fullName = fullName,
        family = family,
        title = title,
        image = image,
        imageUrl = imageUrl
    )
}

private fun List<CharacterRemoteResult>.toLocalModel(): List<CharacterLocalModel> {
    return this.map { it.toLocalModel() }
}




private fun CharacterLocalModel.toDomainModel(): CharacterModel {
    return CharacterModel(
        id = id,
        fullName = if (fullName.isNullOrBlank()) "" else fullName,
        title = if (title.isNullOrBlank()) "" else title,
        family = if (family.isNullOrBlank()) "" else family,
        imageUrl = if (imageUrl.isNullOrBlank()) "" else imageUrl,
        isFavorite = isFavorite
    )
}

private fun List<CharacterLocalModel>.toDomainModel(): List<CharacterModel> {
    return this.map { it.toDomainModel() }
}





