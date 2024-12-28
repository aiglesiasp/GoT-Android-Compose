package com.ipa.dev.gamethrones.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipa.dev.gamethrones.data.local.model.CharacterLocalModel

@Database(entities = [CharacterLocalModel::class], version = 1, exportSchema = false)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
}