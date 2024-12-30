package com.ipa.dev.gamethrones.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterLocalModel")
    fun getCharacters(): Flow<List<CharacterLocalModel>>

    @Query("SELECT * FROM CharacterLocalModel WHERE id = :id")
    fun getCharacter(id: Int): Flow<CharacterLocalModel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterLocalModel>)

}