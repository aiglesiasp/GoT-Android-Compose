package com.ipa.dev.gamethrones.data.remote.api

import com.ipa.dev.gamethrones.data.remote.model.CharacterRemoteResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/v2/Characters")
    suspend fun getCharacters(): List<CharacterRemoteResult>

    @GET("/api/v2/Characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterRemoteResult

}