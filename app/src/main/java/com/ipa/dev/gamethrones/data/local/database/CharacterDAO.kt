package com.ipa.dev.gamethrones.data.local.database

import androidx.room.Dao
import androidx.room.Query
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterLocalModel")
    suspend fun getCharacters(): List<CharacterLocalModel>

    @Query("SELECT * FROM CharacterLocalModel WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterLocalModel

}