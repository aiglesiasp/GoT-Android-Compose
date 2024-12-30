package com.ipa.dev.gamethrones.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterLocalModel")
    suspend fun getCharacters(): List<CharacterLocalModel>

    @Query("SELECT * FROM CharacterLocalModel WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterLocalModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterLocalModel>)

}