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
        val charactersLocal = characterLocalModels.takeIf { it.isNotEmpty() } ?: remoteDataSource.getCharacters().remoteToLocalModel().also { localDataSource.insertAll(it) }
        //Emitimos siempre valores de local
        emit(charactersLocal.localToDomainModel())
    }

    fun getCharacter(id: Int): Flow<CharacterModel?> = localDataSource.getCharacter(id).transform { characterLocalModel ->
        //Comprobar si esta vacia para hacer peticion a RED si es necesario y guardar en base de datos
        val characterLocal = characterLocalModel.takeIf { it != null } ?: remoteDataSource.getCharacter(id).oneRemoteTolLocalModel().also { localDataSource.insertAll(listOf(it)) }

        //Emitimos siempre valores de local
        emit(characterLocal.oneLocalToDomainModel())
    }

    suspend fun toogleFavorite(character: CharacterModel) {

        val characterLocalModel = character.oneDomainToLocalModel()
        localDataSource.insertAll(listOf(characterLocalModel.copy(isFavorite = !characterLocalModel.isFavorite)))
    }
}

private fun CharacterRemoteResult.oneRemoteTolLocalModel(): CharacterLocalModel {
    return CharacterLocalModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        fullName = fullName,
        family = family,
        title = title,
        image = image,
        imageUrl = imageUrl,
        isFavorite = false
    )
}

private fun List<CharacterRemoteResult>.remoteToLocalModel(): List<CharacterLocalModel> {
    return this.map { it.oneRemoteTolLocalModel() }
}




private fun CharacterLocalModel.oneLocalToDomainModel(): CharacterModel {
    return CharacterModel(
        id = id,
        fullName = if (fullName.isNullOrBlank()) "" else fullName,
        title = if (title.isNullOrBlank()) "" else title,
        family = if (family.isNullOrBlank()) "" else family,
        imageUrl = if (imageUrl.isNullOrBlank()) "" else imageUrl,
        isFavorite = isFavorite
    )
}

private fun List<CharacterLocalModel>.localToDomainModel(): List<CharacterModel> {
    return this.map { it.oneLocalToDomainModel() }
}

private fun CharacterModel.oneDomainToLocalModel(): CharacterLocalModel {
    return CharacterLocalModel(
        id = id,
        firstName = "",
        lastName = "",
        fullName = if (fullName.isNullOrBlank()) "" else fullName,
        title = if (title.isNullOrBlank()) "" else title,
        family = if (family.isNullOrBlank()) "" else family,
        image = "",
        imageUrl = if (imageUrl.isNullOrBlank()) "" else imageUrl,
        isFavorite = isFavorite
    )
}

private fun List<CharacterModel>.domainToLocalModel(): List<CharacterLocalModel> {
    return this.map { it.oneDomainToLocalModel() }
}





