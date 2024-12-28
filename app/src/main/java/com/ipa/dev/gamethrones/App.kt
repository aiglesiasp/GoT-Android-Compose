package com.ipa.dev.gamethrones

import android.app.Application
import androidx.room.Room
import com.ipa.dev.gamethrones.data.local.database.CharacterDatabase

class App: Application() {

    lateinit var db: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, CharacterDatabase::class.java, "characters-db").build()
    }
}